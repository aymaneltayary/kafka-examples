package learningjournal.kafka.examples;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class DispatcherDemo {

    public static void main(String[] args) {
        Properties properties= new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.applicationID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        try {
            InputStream fileInputStream=     DispatcherDemo.class
                    .getClassLoader()
                    .getResourceAsStream(AppConfigs.kafkaFileLocation);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try(Producer<Integer,String> kafkaProducer= new KafkaProducer<>(properties)){
          Thread[] activeThreads = new Thread[AppConfigs.fileEvents.length];
           log.info("Starting of dispatcher threads...");
            for(int i=0;i<AppConfigs.fileEvents.length;i++){
                Thread thread= new Thread(new Dispatcher(AppConfigs.topicName,AppConfigs.fileEvents[i],kafkaProducer));
                thread.start();
                activeThreads[i]=thread;

            }
            for(Thread thread:activeThreads){
                thread.join();
            }

            log.info("Dispatcher thread finished. All are done");
        } catch (InterruptedException e) {
            log.error("Main thread Interrupted.");
            throw new RuntimeException(e);
        }


    }
}
