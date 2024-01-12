package guru.learningjournal.kafka.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

import java.util.Properties;

@Slf4j
public class StreamTableApp {

    public static void main(String[] args) {
        Properties streamProperties= new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG,AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOT_STRAP_SERVERS);
        streamProperties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        streamProperties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        streamProperties.put(StreamsConfig.STATE_DIR_CONFIG,AppConfigs.START_STORE_LOCATION);


        StreamsBuilder streamsBuilder= new StreamsBuilder();
        KTable<String, String> kt0= streamsBuilder.table(AppConfigs.TOPIC_NAME,Consumed.with(Serdes.String(),Serdes.String()),Materialized.as(AppConfigs.STATE_STORE_NAME));

        kt0.toStream().foreach((key,value)-> log.info("key={}, message={}",key,value));

       // KTable<String, String> kt1=kt0.filter((key,value)-> key.matches(AppConfigs.REGEX_SYMBOL));//, Materialized.as(AppConfigs.STATE_STORE_NAME));

       // kt1.toStream().foreach((key,value)->log.info("key={}, message={}",key,value));

        KafkaStreams kafkaStreams= new KafkaStreams(streamsBuilder.build(),streamProperties);





        kafkaStreams.start();


        Runtime.getRuntime().addShutdownHook(new Thread(
                ()->{
                    log.info("cleaning resources");
              //      queryServer.stop();
                    kafkaStreams.close();

                }


        ));



    }
}
