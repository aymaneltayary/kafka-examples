package guru.learningjournal.kafka.examples;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
public class HelloProducerSync {

    public static final String SAMPLE_MESSAGE_D = "sample-message-%d";

    public static void main(String[] args) {
        Properties producerProperties= new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.APPLICATION_ID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        log.info("HelloProducerSync has been started... blocking get method will be used ");


        try(Producer<Integer,String> kafkaProducer = new KafkaProducer<>(producerProperties)){

            for (int i=0 ;i<AppConfigs.NUM_EVENTS;i++){
             RecordMetadata metadata= kafkaProducer.send(new ProducerRecord<>(AppConfigs.TOPIC_NAME,i,String.format(SAMPLE_MESSAGE_D,i))).get();
             log.info("Message has been sent to topic={},partition={} with offset={} and on timestamp={}",metadata.topic(),metadata.partition(),metadata.offset(),metadata.timestamp());

            }


        } catch (ExecutionException | InterruptedException e) {
            log.error("Can not send the message.Something went wrong {}", e.getMessage());
            throw new RuntimeException(e);
        }


    }
}
