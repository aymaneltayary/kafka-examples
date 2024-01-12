package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.datagenerator.InvoiceGenerator;
import guru.learningjournal.kafka.examples.serde.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import guru.learningjournal.kafka.examples.model.*;

import java.util.Properties;
@Slf4j
public class PosSimulator {

    public static void main(String[] args) {
        Properties producerProperties= new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfig.CLIENT_ID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        log.info("PosSimulator has been started");
        Producer<String, PosInvoice> producer= new KafkaProducer<>(producerProperties);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutdown..cleaning resource");
            producer.close();

        }));

           while (true){
               PosInvoice invoice= InvoiceGenerator.getInstance().getNextInvoice();
               producer.send(new ProducerRecord<>(AppConfig.POS_TOPIC_NAME,invoice.getStoreId(),invoice));
               log.info("Sending invoice={} has been sent to the topic={}",invoice, AppConfig.POS_TOPIC_NAME);
               try {
                   Thread.sleep(10);
              } catch (InterruptedException e) {
                   log.info("Something went wrong!! {}",e.getMessage());
                   throw new RuntimeException(e);
               }
           }







    }
}
