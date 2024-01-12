package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.DepartmentAggregate;
import guru.learningjournal.kafka.examples.model.Employee;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.util.UUID;

@Slf4j
public class KStreamAggDemo {
    public static void main(String[] args) {

        log.info("KStreamAggDemo has been started");
        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        streamProperties.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STORE_STATE_LOCATION);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, Employee> ks0 = streamsBuilder.stream(AppConfigs.TOPIC_NAME_EMPLOYEE, Consumed.with(AppSerdes.String(), AppSerdes.Employee())).peek((key,value)->log.info("key={},value={}",key,value));

       /*
        using map() method , groupByKey() the reduce


        KStream<String, DepartmentAggregate> ks1=ks0.map((key, value) -> new KeyValue<>(value.getDepartment(),new DepartmentAggregate().withAvgSalary(0D).withEmployeeCount(1).withTotalSalary(value.getSalary())));
        KTable<String, DepartmentAggregate> ks2= ks1.groupByKey(Grouped.with(AppSerdes.String(),AppSerdes.DepartmentAggregate()))
                                                    .reduce((aggregateValue, newValue) -> {
                newValue.setEmployeeCount(aggregateValue.getEmployeeCount()+1);
                newValue.setTotalSalary(aggregateValue.getTotalSalary()+ newValue.getTotalSalary());
                newValue.setAvgSalary((double) (newValue.getTotalSalary()/newValue.getEmployeeCount()));
                return newValue;
            },Materialized.as(UUID.randomUUID().toString()));
        */

        /* Now we are using aggregate

         */
        KTable<String, DepartmentAggregate> ks1= ks0.groupBy((key,value)-> value.getDepartment(),Grouped.with(AppSerdes.String(),AppSerdes.Employee())).aggregate(() ->
                       // Initializer
                        new DepartmentAggregate().withAvgSalary(0D).withEmployeeCount(0).withTotalSalary(0)
                    // Aggregator
                ,(s, employee, departmentAggregate) -> {
                    departmentAggregate.setTotalSalary(departmentAggregate.getTotalSalary()+employee.getSalary());
                    departmentAggregate.setEmployeeCount(departmentAggregate.getEmployeeCount()+1);
                    departmentAggregate.setAvgSalary((double) (departmentAggregate.getTotalSalary()/departmentAggregate.getEmployeeCount()));
                    return departmentAggregate;
                }, //Serializer
                Materialized.<String,DepartmentAggregate, KeyValueStore<Bytes, byte[]>>as(UUID.randomUUID().toString())
                        .withKeySerde(AppSerdes.String())
                        .withValueSerde(AppSerdes.DepartmentAggregate()));

        ks1.toStream().peek((k, v) -> log.info("key={}, message={}", k, v)).to(AppConfigs.TOPIC_NAME_DEPARTMENT, Produced.with(AppSerdes.String(), AppSerdes.DepartmentAggregate()));

        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), streamProperties);

        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("shutdown..clean resources");
            kafkaStreams.cleanUp();


        }));


    }
}
