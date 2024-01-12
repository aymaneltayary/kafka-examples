package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.mapper.PosInvoiceNotificationMapper;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import guru.learningjournal.kafka.examples.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import org.mapstruct.factory.Mappers;

import java.util.Properties;

@Slf4j
public class RewardsApp {
   private final static PosInvoiceNotificationMapper mapper= Mappers.getMapper(PosInvoiceNotificationMapper.class );


    public static void main(String[] args) {

        log.info("RewardsApp has been started");
        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);

        StreamsBuilder streamsBuilder= new StreamsBuilder();
        KStream<String, PosInvoice> ks0=streamsBuilder.stream(AppConfigs.POS_TOPIC_NAME,Consumed.with(AppSerdes.String(),AppSerdes.PosInvoice()));

        KStream<String, Notification> ks1=ks0
                .filter( (k,v)-> AppConfigs.CUSTOMER_TYPE_PRIME.equalsIgnoreCase(v.getCustomerType()))
                .map((storeId, posInvoice) -> new KeyValue<>(posInvoice.getCustomerCardNo(),mapper.posInvoiceToNotification(posInvoice)));

        KGroupedStream<String, Notification> ks2= ks1.groupByKey(Grouped.with(AppSerdes.String(),AppSerdes.Notification()));
        KTable<String, Notification> ks3=ks2.reduce((aggValue, newValue  ) -> {
            newValue.setTotalLoyaltyPoints(aggValue.getTotalLoyaltyPoints()+ newValue.getEarnedLoyaltyPoints());
            return newValue;
        });

        ks3.toStream().peek( (key,value)->  log.info("key={},notification={}",key,value)).to(AppConfigs.NOTIFICATION_TOPIC_NAME,Produced.with(AppSerdes.String(),AppSerdes.Notification()));

        KafkaStreams kafkaStreams= new KafkaStreams(streamsBuilder.build(),streamProperties);
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
          log.info("Shutdown... cleaning the resources");
            kafkaStreams.cleanUp();

        }));

    }
}
