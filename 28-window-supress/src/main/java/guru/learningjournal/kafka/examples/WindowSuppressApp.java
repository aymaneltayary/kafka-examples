package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.HeartBeat;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Properties;
import java.util.UUID;


@Slf4j
public class WindowSuppressApp {

    public static void main(String[] args) {

        log.info("WindowSuppressApp started. Suppressing window ");
        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        streamProperties.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STATE_STORE_LOCATION);
        streamProperties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder = new StreamsBuilder();


        // we determine the considered window time by setting the time extractor
        KStream<String, HeartBeat> ks0 = streamsBuilder
                .stream(AppConfigs.TOPIC_NAME_HEART_BEAT, Consumed.with(AppSerdes.String(), AppSerdes.HeartBeat()).withTimestampExtractor(new HeatBeatTimestampExtractor()));


        // WindowedBy is used after grouping
        KTable<Windowed<String>, Long> kt1 = ks0.peek((key, value) -> log.info("retrieving message from topic={},key={},value={}", AppConfigs.TOPIC_NAME_HEART_BEAT, key, value))
                .groupByKey(Grouped.with(AppSerdes.String(), AppSerdes.HeartBeat()))
                .windowedBy(TimeWindows.ofSizeAndGrace(Duration.ofMinutes(5), Duration.ofMinutes(3)).advanceBy(Duration.ofMinutes(1)))
                .count(Materialized.as(UUID.randomUUID().toString()));
        /*
           Use suppressing window to make sure that the window is closed before applying any other processing
           suppressing window need some buffer to store record till window is closed
         */
        //  .suppress(Suppressed.untilWindowCloses(Suppressed.BufferConfig.unbounded()));

        kt1.toStream().foreach(
                (wKey, value) ->
                        log.info(
                                "Store ID: " + wKey.key() + " Window ID: " + wKey.window().hashCode() +
                                        " Window start: " + Instant.ofEpochMilli(wKey.window().start()).atOffset(ZoneOffset.UTC) +
                                        " Window end: " + Instant.ofEpochMilli(wKey.window().end()).atOffset(ZoneOffset.UTC) +
                                        " Count: " + value + ((value < 3) ? ".The server is not active" : ".")
                        )

        );

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), streamProperties);
        kafkaStreams.start();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Stopping Streams...");
            kafkaStreams.close();
        }));

    }
}
