package guru.learningjournal.kafka.examples;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.learningjournal.kafka.examples.model.Notification;
import guru.learningjournal.kafka.examples.model.PosInvoice;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import guru.learningjournal.kafka.examples.topology.RewardTopology;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class RewardTopologyTest {

    Properties streamProperties= new Properties();
    private TopologyTestDriver topologyTestDriver;
    private TestInputTopic<String, PosInvoice> inputTopic;
    private TestOutputTopic<String, Notification> outputTopic;



    @BeforeEach
     void setup(){
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
        topologyTestDriver= new TopologyTestDriver(RewardTopology.build(),streamProperties);
        inputTopic=topologyTestDriver.createInputTopic(AppConfig.POS_TOPIC_NAME
                , AppSerdes.String().serializer()
                , AppSerdes.PosInvoice().serializer());

        outputTopic=topologyTestDriver.createOutputTopic(AppConfig.NOTIFICATION_TOPIC_NAME
                , AppSerdes.String().deserializer()
                , AppSerdes.Notification().deserializer());
    }

    @Test
    void buildTest(){
         createInvoiceList().forEach(inv->{
             TestRecord<String, PosInvoice> testRecord= new TestRecord<>(inv.getStoreId(),inv);
             inputTopic.pipeInput(testRecord);
         });
        Assertions.assertEquals(45L,outputTopic.readRecordsToList().size());
        KeyValueStore<String, Double> stateStore= topologyTestDriver.getKeyValueStore(AppConfig.REWARDS_STORE_NAME);
        Assertions.assertNotNull(stateStore);
        Assertions.assertEquals(120.0,stateStore.get("5582740626"));


   }

    @AfterEach
    void clean (){
        topologyTestDriver.close();
    }


    private List<PosInvoice> createInvoiceList() {
        String DATAFILE = "src/test/resources/data/Invoice.json";
        ObjectMapper mapper;
        Random numberOfItems = new Random();
        PosInvoice[] invoices;
        mapper = new ObjectMapper();

        try {
          return List.of(invoices = mapper.readValue(new File(DATAFILE), PosInvoice[].class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
