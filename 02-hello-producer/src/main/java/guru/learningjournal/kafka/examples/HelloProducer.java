package guru.learningjournal.kafka.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


@Slf4j
public class HelloProducer {


    private static final Logger logger= LoggerFactory.getLogger(HelloProducer.class);
    public static final String SAMPLE_MESSAGE_D = "sample-message-%d";

    public static void main(String[] args) {
        Properties properties= new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.APPLICATION_ID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOT_STRAP_SERVERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        ProducerConfig producerConfig= new ProducerConfig(properties);

       log.info("HelloProducer is starting asynchronous sending");
        try( KafkaProducer<Integer,String> producer= new KafkaProducer<>(properties)){
           for (int i=1; i<=AppConfigs.NUM_EVENTS;i++){
               producer.send(new ProducerRecord<>(AppConfigs.TOPIC_NAME,i,String.format(SAMPLE_MESSAGE_D,i)));
           }
       }
        log.info("HelloProducer finished asynchronous sending");



    }
}
