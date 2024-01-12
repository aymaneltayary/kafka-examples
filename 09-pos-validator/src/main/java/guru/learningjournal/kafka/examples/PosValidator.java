package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.*;
import guru.learningjournal.kafka.examples.serde.JsonDeserializer;
import guru.learningjournal.kafka.examples.serde.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class PosValidator {


    public static final String HOME_DELIVERY = "HOME-DELIVERY";

    public static void main(String[] args) {

        Properties consumerProperties= new Properties();
        consumerProperties.put(ConsumerConfig.CLIENT_ID_CONFIG,AppConfigs.APPLICATION_ID);
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOTSTRAP_SERVERS);
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerProperties.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, PosInvoice.class);
        consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG,AppConfigs.GROUP_ID);
        consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        Properties producerProperties= new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.APPLICATION_ID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOTSTRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        try(Consumer<String, PosInvoice> kafkaConsumer= new KafkaConsumer<String, PosInvoice>(consumerProperties);
            Producer<String,PosInvoice> producer= new KafkaProducer<>(producerProperties)){
            kafkaConsumer.subscribe(Arrays.asList(AppConfigs.SOURCE_TOPIC_NAMES) );
            while(true){
                ConsumerRecords<String, PosInvoice> records=kafkaConsumer.poll(Duration.ofMillis(100));
                records.forEach(record->{
                    if(HOME_DELIVERY.equals(record.value().getDeliveryType())&&"".equals(record.value().getDeliveryAddress().getContactNumber())   ){
                         log.info("Invalid invoice={}",record.value());
                            producer.send(new ProducerRecord<>(AppConfigs.INVALID_TOPIC_NAME,record.value().getInvoiceNumber(),record.value()));
                    } else {
                        producer.send(new ProducerRecord<>(AppConfigs.VALID_TOPIC_NAME,record.value().getInvoiceNumber(),record.value()));
                        log.info("Valid invoice={}",record.value());
                    }
                });

            }


        }
    }
}
