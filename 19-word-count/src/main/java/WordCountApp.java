import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class WordCountApp {

    public static void main(final String[] args) {

        log.info("WordCountApp app has been started");

        Properties streamProperties= new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG,AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        streamProperties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        streamProperties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);

        // We are going to create a KTable to store the word count.
        //
        //Creating a KTable would internally create a local state store. Right?
        //
        //We do not want Kafka to generate the state store at a default location.
        //
        //Hence, we specify a root directory for the Rocks DB state store.

        streamProperties.put(StreamsConfig.STATE_DIR_CONFIG,AppConfigs.STATE_STORE_LOCATION);

        StreamsBuilder streamsBuilder= new StreamsBuilder();

        KStream<String,String> ks0=streamsBuilder.stream(AppConfigs.TOPIC_NAME);

        KStream<String,String> ks1= ks0.flatMapValues((value) -> Arrays.asList(value.split(" ")) );

        KGroupedStream<String, String> ks2=ks1.groupBy((k, v) ->v );
        KTable<String,Long> ks3=  ks2.count();

        ks3.toStream().foreach((k,v)-> log.info("Now after grouping....key={} , value={}",k,v));

        KafkaStreams kafkaStreams= new KafkaStreams(streamsBuilder.build(),streamProperties);
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            log.info("shutdown. cleaning resources");
            kafkaStreams.close();

        }));


    }
}
