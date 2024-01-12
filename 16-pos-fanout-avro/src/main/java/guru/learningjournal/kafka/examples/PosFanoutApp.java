package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.PosInvoice;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;


@Slf4j
public class PosFanoutApp {
    private static final Logger logger = LogManager.getLogger();

    private final static String RED = "\u001B[31m";
    private final static String GREEN = "\u001B[32m";
    private final static  String YELLOW = "\u001B[33m";


    public static void main(String[] args) {
        Properties streamProperties= new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG,AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOT_STRAP_SERVERS);

        StreamsBuilder streamsBuilder= new StreamsBuilder();
        KStream<String, PosInvoice> ks0=streamsBuilder.stream(AppConfigs.POS_TOPIC_NAME,Consumed.with(AppSerdes.String(),AppSerdes.PosInvoice()));

        ks0.filter((s, posInvoice) ->AppConfigs.DELIVERY_TYPE_HOME_DELIVERY.equals(posInvoice.getDeliveryType().toString()))
                . peek((s, posInvoice) -> log.info(RED+"Getting posInvoice={} to topic={} ",posInvoice,AppConfigs.SHIPMENT_TOPIC_NAME))
                        .to(AppConfigs.SHIPMENT_TOPIC_NAME,Produced.with(AppSerdes.String(),AppSerdes.PosInvoice()));



        ks0.filter((key,value)->AppConfigs.CUSTOMER_TYPE_PRIME.equals(value.getCustomerType().toString()))
                .mapValues(RecordBuilder::getNotification)
                .peek((s, notification) -> log.info(GREEN+"Sending notification={} to topic={} ",notification,AppConfigs.LOYALTY_TOPIC_NAME))
                .to(AppConfigs.LOYALTY_TOPIC_NAME,Produced.with(AppSerdes.String(),AppSerdes.Notification()));

        ks0.mapValues(RecordBuilder::getMaskedInvoice)
                .flatMapValues(RecordBuilder::getHadoopRecords).
                peek((s, hadoopRecord) -> log.info(YELLOW+"Sending hadoopRecord={} to topic={} ",hadoopRecord,AppConfigs.HADOOP_TOPIC_NAME)).
                to(AppConfigs.HADOOP_TOPIC_NAME,Produced.with(AppSerdes.String(),AppSerdes.HadoopRecord()));

                Topology topology=streamsBuilder.build();
                KafkaStreams kafkaStreams= new KafkaStreams(topology,streamProperties);
                kafkaStreams.start();


                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    log.info("cleaning resource");
                    kafkaStreams.close();
                }));


    }
}
