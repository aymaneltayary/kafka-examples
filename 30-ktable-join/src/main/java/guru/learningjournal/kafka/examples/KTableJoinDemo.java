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
import java.util.UUID;


@Slf4j
public class KTableJoinDemo {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STATE_STORE_LOCATION_TMP);
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        log.info("KTableJoinDemo has started");
        StreamsBuilder streamsBuilder= new StreamsBuilder();
        KTable<String,UserDetails> detailKtable= streamsBuilder.table(AppConfigs.TOPIC_NAME_USER_MASTER,Consumed.with(AppSerdes.String(),AppSerdes.userDetails()),Materialized.as(UUID.randomUUID().toString()));
        KTable<String,UserLogin> loginKTable= streamsBuilder.table(AppConfigs.TOPIC_NAME_USER_LOGIN,Consumed.with(AppSerdes.String(),AppSerdes.userLogin()),Materialized.as(UUID.randomUUID().toString()));

        detailKtable.join(loginKTable,
                (userDetails, userLogin) -> {userDetails.setLastLogin(userLogin.getCreatedTime()); return userDetails;} )
                .toStream()
                .foreach((key,value)->{
                    log.info("key={},value={}",key, value);
                });

        KafkaStreams kafkaStreams= new KafkaStreams(streamsBuilder.build(),props);
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            log.info("shutdown the app.cleaning resources");
            kafkaStreams.close();

        }));






    }
}
