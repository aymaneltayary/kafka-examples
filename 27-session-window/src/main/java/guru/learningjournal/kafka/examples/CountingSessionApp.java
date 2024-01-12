package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.UserClick;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.FailOnInvalidTimestamp;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Properties;


@Slf4j
public class CountingSessionApp {


    public static void main(String[] args) {
        log.info("CountingSessionApp started. session windows");
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STATE_STORE_LOCATION);
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, FailOnInvalidTimestamp.class);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, UserClick> ks0 = streamsBuilder.stream(AppConfigs.TOPIC_NAME_USER_CLICKS, Consumed.with(AppSerdes.String(), AppSerdes.UserClick()).withTimestampExtractor(new UserClickTimestampExtractor()));

        KTable<Windowed<String>, Long> kt1 = ks0
                .groupByKey(Grouped.with(AppSerdes.String(), AppSerdes.UserClick()))
                .windowedBy(SessionWindows.ofInactivityGapWithNoGrace(Duration.ofMinutes(5)))
                .count();

        kt1.toStream().foreach(
                (wKey, value) ->
                        log.info(
                                "Store ID: " + wKey.key() + " Window ID: " + wKey.window().hashCode() +
                                        " Window start: " + Instant.ofEpochMilli(wKey.window().start()).atOffset(ZoneOffset.UTC) +
                                        " Window end: " + Instant.ofEpochMilli(wKey.window().end()).atOffset(ZoneOffset.UTC) +
                                        " Count: " + value)


        );


        log.info("Starting Stream...");
        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Stopping Streams...");
            streams.close();
        }));

    }
}
