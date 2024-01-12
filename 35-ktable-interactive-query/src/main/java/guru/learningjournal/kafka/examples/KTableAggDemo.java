package guru.learningjournal.kafka.examples;


import com.fasterxml.jackson.databind.ObjectMapper;
import guru.learningjournal.kafka.examples.model.Employee;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import spark.Spark;

import java.util.Properties;
import static spark.Spark.*;

@Slf4j
public class KTableAggDemo {

   private static ObjectMapper mapper= new ObjectMapper();

    public static void main(String[] args) throws InterruptedException {

        log.info("interactive query started");

        Properties strmProperties = new Properties();
        strmProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.APPLICATION_ID);
        strmProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
        strmProperties.put(StreamsConfig.STATE_DIR_CONFIG, AppConfig.STATE_STORE_LOCATION_TMP);
        strmProperties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);
        strmProperties.put(StreamsConfig.APPLICATION_SERVER_CONFIG, "localhost:8080");


        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KTable<String, Employee> empKtable = streamsBuilder.table(AppConfig.TOPIC_NAME_EMPLOYEE, Consumed.with(AppSerdes.String(), AppSerdes.Employee()), Materialized.as(AppConfig.STATE_STORE_NAME_REPORT));

        empKtable.toStream().foreach((key, value) -> log.info("printing ktable event content key={},value={}", key, value));
        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), strmProperties);

        kafkaStreams.setStateListener((currentState, prevState) -> {
            if(KafkaStreams.State.RUNNING.equals(currentState) && KafkaStreams.State.REBALANCING.equals(prevState)) {
                log.info("State Changing to " + currentState + " from " + prevState);
                //  Spark.port(8080);
               Spark.get("/employees/:id", (req, res) -> {

                    ReadOnlyKeyValueStore<String, Employee> keyValueStore =
                            kafkaStreams.store(StoreQueryParameters.fromNameAndType(
                                    AppConfig.STATE_STORE_NAME_REPORT, QueryableStoreTypes.keyValueStore()));
                    Employee record= keyValueStore.get(req.params(":id"));

                    return mapper.writeValueAsString(record);

                });
            }
        });
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutdown.. cleaning resources");
            kafkaStreams.cleanUp();
            kafkaStreams.start();
        }));

      //  Thread.sleep(60000);





    }


}
