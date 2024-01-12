package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.aggregate.model.TopThreeCount;
import guru.learningjournal.kafka.examples.model.Inventory;
import guru.learningjournal.kafka.examples.model.TypeCount;
import guru.learningjournal.kafka.examples.model.UserClick;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Properties;
import java.util.UUID;

@Slf4j
public class TopThreeCountDemo {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STATE_STORE_LOCATION_TMP);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        log.info("GKTableJoinDemo has started");
        StreamsBuilder streamsBuilder= new StreamsBuilder();

        GlobalKTable<String, Inventory> gkt=streamsBuilder.globalTable(AppConfigs.TOPIC_NAME_ACTIVE_INVENTORY, Consumed.with(AppSerdes.String(),AppSerdes.Inventory()));
        KStream<String, UserClick> ks=streamsBuilder.stream(AppConfigs.TOPIC_NAME_USER_CLICKS,Consumed.with(AppSerdes.String(),AppSerdes.userClick()));



        // grouping& count aggregation
        KTable<String,Long> resultGroup= ks
                .join(gkt,(s, userClick) -> s,(userClick, inventory) -> inventory )
                .groupBy((s, inventory) -> inventory.getNewsType(), Grouped.with(AppSerdes.String(),AppSerdes.Inventory()))
                .count();

        resultGroup
                .groupBy((s, aLong) -> KeyValue.pair("A", new TypeCount().withNewsType(s).withCount(Double.valueOf(aLong.doubleValue()))),Grouped.with(AppSerdes.String(),AppSerdes.typeCount()))
                .aggregate(() -> {
                    TopThreeCount topThreeCount= new TopThreeCount();
                    return topThreeCount;
                },(s, typeCount, topThreeCount) -> {
                    topThreeCount.addElement(typeCount);
                    return topThreeCount;
                },(s, typeCount, topThreeCount) -> {
                    topThreeCount.removeElement(typeCount);
                    return topThreeCount;
                },Materialized.<String,TopThreeCount, KeyValueStore<Bytes, byte[]>>as(AppConfigs.TOPIC_NAME_TOP3_NEWS_TYPE)
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.topThreeCount())).toStream().foreach((key,value)->log.info("Now printing the top ten messages. key={} value={}",key,value));

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Stopping Streams...");
            streams.close();
        }));

    }
    }

