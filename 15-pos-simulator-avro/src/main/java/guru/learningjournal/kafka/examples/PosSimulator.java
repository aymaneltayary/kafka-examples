package guru.learningjournal.kafka.examples;


import guru.learningjournal.kafka.examples.model.PosInvoice;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;


import java.util.Properties;
import java.util.concurrent.*;

@Slf4j
public class PosSimulator {


   private final static String RED = "\u001B[31m";
    private final static String GREEN = "\u001B[32m";
    private final static  String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.APPLICATION_ID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        producerProperties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, AppConfigs.SCHEME_REGISTRY_SERVERS);



        log.info("PosSimulator has been started");
        Producer<String, PosInvoice> kafkaProducer = new KafkaProducer<String, PosInvoice>(producerProperties) ;
            ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.submit(new RunnableProducer(1,RED,100,AppConfigs.TOPIC_NAME, kafkaProducer));
            executorService.submit(new RunnableProducer(2,GREEN,100, AppConfigs.TOPIC_NAME, kafkaProducer));
            executorService.submit(new RunnableProducer(3,YELLOW,100, AppConfigs.TOPIC_NAME, kafkaProducer));
           executorService.shutdown();
          try {
                if (!executorService.awaitTermination(300000, TimeUnit.MILLISECONDS)) {
                    log.info("cleaning resources");
                    executorService.shutdownNow();
                    kafkaProducer.close();
                }
            } catch (InterruptedException e) {
              executorService.shutdownNow();

            }
            log.info("PosSimulator Finished");
    }
}