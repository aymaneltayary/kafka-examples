package guru.learningjournal.kafka.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.UUID;


@Slf4j
public class AgeCountDemo {

    public static void main(String[] args) {
        log.info("AgeCountDemo have been started");

        Properties streamProperties= new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG,AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOT_STRAP_SERVERS);

        StreamsBuilder streamsBuilder= new StreamsBuilder();
        KTable<String,String> kt0=streamsBuilder.table(AppConfigs.TOPIC_NAME_PERSON_AGE,Consumed.with(Serdes.String(), Serdes.String()));
        KTable<String,Long> kt1=kt0.groupBy((key,value)-> KeyValue.pair(value,0L),Grouped.with(Serdes.String(),Serdes.Long())).count(Materialized.as(UUID.randomUUID().toString()));

        kt1.toStream().foreach((key,value)->log.info ("age={},count={}",key,value));
        KafkaStreams kafkaStream= new KafkaStreams(streamsBuilder.build(),streamProperties);

        kafkaStream.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            log.info("Closing the app");
        kafkaStream.cleanUp();

        }));




    }

}
