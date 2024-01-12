package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.DepartmentAggregate;
import guru.learningjournal.kafka.examples.model.Employee;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.UUID;


@Slf4j
public class KTableAggDemo {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        log.info("KStreamAggDemo has been started");
        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        streamProperties.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STORE_STATE_LOCATION);
        streamProperties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG,100);

        StreamsBuilder streamsBuilder= new StreamsBuilder();
        KTable<String, Employee> kt0= streamsBuilder.table(AppConfigs.TOPIC_NAME_EMPLOYEE, Consumed.with(AppSerdes.String(),AppSerdes.Employee()));
        //kt0.groupBy((key, value) -> KeyValue.pair (value.getDepartment(),new DepartmentAggregate().withEmployeeCount(0).withTotalSalary(0).withAvgSalary(0D)),Grouped.with(AppSerdes.String(),AppSerdes.DepartmentAggregate()));

       KTable<String,DepartmentAggregate> kt1=
               //kt0.groupBy((key, value) -> KeyValue.pair (value.getDepartment(),new DepartmentAggregate().withEmployeeCount(0).withTotalSalary(0).withAvgSalary(0D)),Grouped.with(AppSerdes.String(),AppSerdes.DepartmentAggregate()))
               kt0.groupBy((key, value) -> KeyValue.pair (value.getDepartment(),value),Grouped.with(AppSerdes.String(),AppSerdes.Employee()))
                        .aggregate(() ->
                            // Initializer

                            new DepartmentAggregate().withEmployeeCount(0).withTotalSalary(0).withAvgSalary(0D)

                        ,(s, employee, departmentAggregate) -> {
                                    departmentAggregate.setTotalSalary(departmentAggregate.getTotalSalary()+employee.getSalary());
                                    departmentAggregate.setEmployeeCount(departmentAggregate.getEmployeeCount()+1);
                                    departmentAggregate.setAvgSalary((double) (departmentAggregate.getTotalSalary()/departmentAggregate.getEmployeeCount()));
                                    return departmentAggregate;
                                },
                                (s, employee, departmentAggregate) -> {
                                    departmentAggregate.setTotalSalary(departmentAggregate.getTotalSalary()-employee.getSalary());
                                    departmentAggregate.setEmployeeCount(departmentAggregate.getEmployeeCount()-1);
                                    departmentAggregate.setAvgSalary((double) (departmentAggregate.getTotalSalary()/departmentAggregate.getEmployeeCount()));
                                    return departmentAggregate;

                                },   Materialized.<String,DepartmentAggregate, KeyValueStore<Bytes, byte[]>>as(UUID.randomUUID().toString())
                                        .withKeySerde(AppSerdes.String())
                                        .withValueSerde(AppSerdes.DepartmentAggregate()));

        kt1.toStream().foreach( (key,value)->  log.info("key={},value={}",key,value));

        KafkaStreams kafkaStreams= new KafkaStreams(streamsBuilder.build(),streamProperties);
        kafkaStreams.start();



        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping Streams");
            kafkaStreams.close();
        }));
    }
}
