package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.PosInvoice;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class PosConsumer {


    public static void main(String[] args) {
        Properties consumerProperties= new Properties();
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG,AppConfigs.APPLICATION_ID);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG,AppConfigs.CLIENT_ID);
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOT_STRAP_SERVERS);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        consumerProperties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, AppConfigs.SCHEME_REGISTRY_SERVERS);
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        Consumer<String, PosInvoice> kafkaConsumer= new KafkaConsumer<String,PosInvoice>(consumerProperties);
        kafkaConsumer.subscribe(Arrays.asList(new String[]{AppConfigs.TOPIC_NAME}));
        log.info("PosConsumer has been started");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("PosConsumer has been closed.Cleaning resource");
            kafkaConsumer.close();

        }));

            while(true){
              ConsumerRecords<String, PosInvoice> records= kafkaConsumer.poll(Duration.ofMillis(100));
                records.forEach(record->{
                    log.info("getting invoice={}",record.value());
                });


            }



        };


    }

