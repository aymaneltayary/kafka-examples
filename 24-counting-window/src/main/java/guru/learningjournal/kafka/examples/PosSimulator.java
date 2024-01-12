package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.PosInvoice;
import guru.learningjournal.kafka.examples.serde.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Slf4j
public class PosSimulator {

    public static void main(String[] args) {
        Properties producerProperties = new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.APPLICATION_ID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        log.info("PosSimulator has been started");
        Producer<String, PosInvoice> producer = new KafkaProducer<>(producerProperties);
        List<PosInvoice> list = new ArrayList<>();

        PosInvoice invoice = new PosInvoice().withStoreId("STR1534").withInvoiceNumber("101").withCreatedTime("2019-02-05T10:00:10.00Z").withTotalAmount(1920D);
        list.add(invoice);
        invoice = new PosInvoice().withStoreId("STR1535").withInvoiceNumber("102").withCreatedTime("2019-02-05T10:00:40.00Z").withTotalAmount(1920D);
        list.add(invoice);
        invoice = new PosInvoice().withStoreId("STR1534").withInvoiceNumber("103").withCreatedTime("2019-02-05T10:01:11.00Z").withTotalAmount(1920D);
        list.add(invoice);
        invoice = new PosInvoice().withStoreId("STR1535").withInvoiceNumber("104").withCreatedTime("2019-02-05T10:02:11.00Z").withTotalAmount(1920D);
        list.add(invoice);
        invoice = new PosInvoice().withStoreId("STR1535").withInvoiceNumber("105").withCreatedTime("2019-02-05T10:03:15.00Z").withTotalAmount(1920D);
        list.add(invoice);
        invoice = new PosInvoice().withStoreId("STR1534").withInvoiceNumber("106").withCreatedTime("2019-02-05T10:04:12.00Z").withTotalAmount(1920D);
        list.add(invoice);
        invoice = new PosInvoice().withStoreId("STR1534").withInvoiceNumber("107").withCreatedTime("2019-02-05T10:07:11.00Z").withTotalAmount(1920D);
        list.add(invoice);

        for (PosInvoice posInvoice : list) {
            producer.send(new ProducerRecord<>(AppConfigs.TOPIC_NAME_POS, posInvoice.getStoreId(), posInvoice));
            log.info("Sending invoice={} has been sent to the topic={}", posInvoice, AppConfigs.TOPIC_NAME_POS);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.info("Something went wrong!! {}", e.getMessage());
                throw new RuntimeException(e);
            }

        }

        log.info("PosSimulator finished. {} have been sent to topic={}", list.size(), AppConfigs.TOPIC_NAME_POS);

        producer.close();


    }
}
