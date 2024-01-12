package guru.learningjournal.kafka.examples;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;


import java.util.Properties;


@Slf4j
public class AdvertCTRDemo {

    public static void main(String[] args) {
        log.info("AdvertismentDemo started");

        Properties strmProps= new Properties();
        strmProps.put(StreamsConfig.APPLICATION_ID_CONFIG,AppConfig.APPLICATION_ID);
        strmProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfig.BOOT_STRAP_SERVERS);
        strmProps.put(StreamsConfig.STATE_DIR_CONFIG,AppConfig.STATE_STORE_LOCATION_TMP);
        strmProps.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);
        StreamsBuilder streamsBuilder= new StreamsBuilder();

        KafkaStreams kafkaStreams = new KafkaStreams( new KafkaService().buildTopology(streamsBuilder),strmProps);
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutdown Cleaning resources");
            kafkaStreams.close();
            //kafkaStreams.cleanUp();

        }));










    }


}
