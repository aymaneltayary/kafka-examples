package guru.learningjournal.kafka.examples;


import guru.learningjournal.kafka.examples.config.AppConfig;
import guru.learningjournal.kafka.examples.datagenerator.InvoiceGenerator;
import guru.learningjournal.kafka.examples.serde.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import guru.learningjournal.kafka.examples.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.*;

@Slf4j
public class PosSimulator {


    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(PosSimulator.class.getClassLoader().getResourceAsStream(AppConfig.KAFKA_PROPERTY_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfig.APPLICATION_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        log.info("PosSimulator has been started. list of invoice will be generated");
        ExecutorService executorService = Executors.newFixedThreadPool(AppConfig.NUM_EVENTS);
        try (Producer<String, PosInvoice> kafkaProducer = new KafkaProducer<>(properties)) {
            List<Future<?>> futureList= new ArrayList<>();

            for (int i = 0; i < AppConfig.NUM_EVENTS; i++) {
                List<PosInvoice> invoiceList = new ArrayList<>();
                for (int j = 0; j < 1000; j++) {
                    invoiceList.add(InvoiceGenerator.getInstance().getNextInvoice());
                }
                Thread runnableProducer = new Thread(new RunnableProducer(i + 1, kafkaProducer, invoiceList, AppConfig.TOPIC_NAME));
                Future<?> future= executorService.submit(runnableProducer);
                futureList.add(future);
            }
           for (Future<?> future:futureList){
               future.get();

           }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();
       log.info("PosSimulator finished. All threads finished.");

    }
}