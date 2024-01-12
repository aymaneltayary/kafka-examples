package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.topology.HelloTopology;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

@Slf4j
public class HelloStreams {

    public static void main(String[] args) {

        Properties properties= new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.APPLICATION_ID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.IntegerSerde.class);
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.StringSerde.class);
        KafkaStreams streams= new KafkaStreams(HelloTopology.build(), properties);
        streams.start();

      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Clean resources....closing the stream");
            streams.close();
       }));

    }
}
