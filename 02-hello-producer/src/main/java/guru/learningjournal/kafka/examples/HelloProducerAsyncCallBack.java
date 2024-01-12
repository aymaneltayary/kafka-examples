package guru.learningjournal.kafka.examples;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class HelloProducerAsyncCallBack {

    public static final String SAMPLE_MESSAGE_D = "sample-message-%d";

    public static void main(String[] args) {

        Properties producerProperties= new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.APPLICATION_ID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        log.info("HelloProducerAsyncCallBack has been started... callback will be used ");

        try(Producer<Integer,String> kafkaProducer= new KafkaProducer<>(producerProperties)){

            for(int i=0;i<AppConfigs.NUM_EVENTS;i++){
                kafkaProducer.send(new ProducerRecord<>(AppConfigs.TOPIC_NAME, i, String.format(SAMPLE_MESSAGE_D, i)), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if(e==null){
                            log.info("Message has been sent to topic={},partition={} with offset={} and on timestamp={}",recordMetadata.topic(),recordMetadata.partition(),recordMetadata.offset(),recordMetadata.timestamp());
                        }else{
                            log.error("Something went wrong while sending the message {}",e.getMessage());
                        }

                    }
                });
            }



        }


    }
}
