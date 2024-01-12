package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import guru.learningjournal.kafka.examples.model.*;

import java.util.Properties;

@Slf4j
public class PosFanoutApp {


    public static void main(String[] args) {
        Properties streamProperties= new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
        StreamsBuilder streamsBuilder= new StreamsBuilder();
        KStream<String, PosInvoice> ks0=  streamsBuilder.stream(AppConfig.POS_TOPIC_NAME, Consumed.with(AppSerdes.String(),AppSerdes.PosInvoice()));
        ks0.filter((key,value)-> AppConfig.DELIVERY_TYPE_HOME_DELIVERY.equals(value.getDeliveryType()))

                .peek((s, posInvoice) -> log.info("Sending posInvoice={} to topic={} ",posInvoice, AppConfig.SHIPMENT_TOPIC_NAME))
                .to(AppConfig.SHIPMENT_TOPIC_NAME, Produced.with(AppSerdes.String(),AppSerdes.PosInvoice()));



        ks0.filter((key,value)-> AppConfig.CUSTOMER_TYPE_PRIME.equals(value.getCustomerType()))
                .mapValues(RecordBuilder::getNotification)
                .peek((s, notification) -> log.info("Sending notification={} to topic={} ",notification, AppConfig.LOYALTY_TOPIC_NAME))
                .to(AppConfig.LOYALTY_TOPIC_NAME,Produced.with(AppSerdes.String(),AppSerdes.Notification()));

        ks0.mapValues(RecordBuilder::getMaskedInvoice)
                .flatMapValues(RecordBuilder::getHadoopRecords).
                peek((s, hadoopRecord) -> log.info("Sending hadoopRecord={} to topic={} ",hadoopRecord, AppConfig.HADOOP_TOPIC_NAME)).
                to(AppConfig.HADOOP_TOPIC_NAME,Produced.with(AppSerdes.String(),AppSerdes.HadoopRecord()));

        Topology topology=streamsBuilder.build();


        KafkaStreams kafkaStreams= new KafkaStreams(topology,streamProperties);
        kafkaStreams.start();



        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("cleaning resource");
            kafkaStreams.close();
        }));








    }
}
