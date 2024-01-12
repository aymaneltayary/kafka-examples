package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.*;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import guru.learningjournal.kafka.examples.topology.RewardTopology;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Repartitioned;
import org.apache.kafka.streams.processor.StreamPartitioner;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import java.util.Properties;

@Slf4j
public class RewardsApp {

    public static void main(String[] args) {
        Properties streamProperties= new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
        KafkaStreams kafkaStreams= new KafkaStreams(RewardTopology.build(),streamProperties);
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Cleaning resources");
            kafkaStreams.cleanUp();

        }));


    }
}
