package guru.learningjournal.kafka.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
@Slf4j
public class HelloProducer {


    public static void main(String[] args) {

        log.info("Creating Kafka Producer...");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfig.PRODUCER_APPLICATION_ID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);

        log.info("Start sending messages...");
        for (int i = 1; i <= AppConfig.NUM_EVENTS; i++) {
            producer.send(new ProducerRecord<>(AppConfig.TOPIC_NAME, i, "Simple Message-" + i));
        }

        log.info("Finished - Closing Kafka Producer.");
        producer.close();

    }
}
