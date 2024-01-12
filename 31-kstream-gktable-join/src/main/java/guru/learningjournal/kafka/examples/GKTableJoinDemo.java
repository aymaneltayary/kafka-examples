package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.serde.AppSerdes;
import guru.learningjournal.kafka.examples.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;


@Slf4j
public class GKTableJoinDemo {


    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STATE_STORE_LOCATION_TMP);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        log.info("GKTableJoinDemo has started");
        StreamsBuilder streamsBuilder= new StreamsBuilder();

        GlobalKTable<String,Inventory> gkt=streamsBuilder.globalTable(AppConfigs.TOPIC_NAME_ACTIVE_INVENTORY,Consumed.with(AppSerdes.String(),AppSerdes.Inventory()));
        KStream<String,UserClick> ks=streamsBuilder.stream(AppConfigs.TOPIC_NAME_USER_CLICKS,Consumed.with(AppSerdes.String(),AppSerdes.userClick()));


        KTable<String,Long> result= ks
                .join(gkt,(s, userClick) -> s,(userClick, inventory) -> inventory )
                .groupBy((s, inventory) -> inventory.getNewsType(),Grouped.with(AppSerdes.String(),AppSerdes.Inventory()))
                        .count();

        log.info("start printing the grouping result..........");
        result.toStream().foreach((key,value)->log.info("key={}, value={}",key,value));


         KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Stopping Streams...");
            streams.close();
        }));

    }
}
