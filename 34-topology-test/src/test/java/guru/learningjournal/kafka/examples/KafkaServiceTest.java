package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.CampaignPerformance;
import guru.learningjournal.kafka.examples.model.Click;
import guru.learningjournal.kafka.examples.model.Impression;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.kafka.streams.test.*;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  //Tell that we need to apply the method ordering while executing
class KafkaServiceTest {
    public static final String CAMPAIGN_NAME_ABC_LTD = "ABC Ltd";
    private static TopologyTestDriver topologyTestDriver;
    private static  TestInputTopic<String, Impression> impressionInputTopic;
    private static  TestInputTopic<String, Click> clickInputTopic;

    private static TestOutputTopic<String, CampaignPerformance> campaignOutPutTopic;


    /**
     * To be executed before all test cases
     */
    @BeforeAll
    static void  setup(){

        Properties strmProps= new Properties();
        strmProps.put(StreamsConfig.APPLICATION_ID_CONFIG,AppConfig.APPLICATION_ID);
        strmProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfig.BOOT_STRAP_SERVERS);
        strmProps.put(StreamsConfig.STATE_DIR_CONFIG,AppConfig.STATE_STORE_LOCATION_TEST);
        strmProps.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 0);

        StreamsBuilder streamsBuilder= new StreamsBuilder();

        topologyTestDriver = new TopologyTestDriver(new KafkaService().buildTopology(streamsBuilder),strmProps);
        /*
        Creating the input and the output topics
         */
        impressionInputTopic = topologyTestDriver.createInputTopic(AppConfig.TOPIC_NAME_IMPRESSION, AppSerdes.String().serializer(),AppSerdes.impression().serializer());
        clickInputTopic = topologyTestDriver.createInputTopic(AppConfig.TOPIC_NAME_CLICK, AppSerdes.String().serializer(),AppSerdes.click().serializer());
        campaignOutPutTopic = topologyTestDriver.createOutputTopic(AppConfig.TOPIC_CAMPAIGN_PERFORMANCE,AppSerdes.String().deserializer(),AppSerdes.CampaignPerformance().deserializer());

    }

    @Test
    @Order(1)
    @DisplayName("Test the count when only one impression is sent")
    void sendImpressionTest(){
        Impression impression = new Impression().withImpressionId("100001").withCampaigner(CAMPAIGN_NAME_ABC_LTD);
        TestRecord<String,Impression> testRecord= new TestRecord<String, Impression>(impression.getImpressionId(),impression);
        impressionInputTopic.pipeInput(testRecord);
        TestRecord<String,CampaignPerformance> outRec= campaignOutPutTopic.readRecord();
        assertAll(() -> assertEquals(CAMPAIGN_NAME_ABC_LTD,outRec.getValue().getCampaigner())   ,() -> assertEquals(1D,outRec.getValue().getImpressionCount()))  ;
    }


    @Test
    @Order(2)
    @DisplayName("Test the count when another impression is sent")
    void sendAnotherImpressionTest(){
        Impression impression = new Impression().withImpressionId("100002").withCampaigner(CAMPAIGN_NAME_ABC_LTD);
        TestRecord<String,Impression> testRecord= new TestRecord<String, Impression>(impression.getImpressionId(),impression);
        impressionInputTopic.pipeInput(testRecord);
        TestRecord<String,CampaignPerformance> outRec= campaignOutPutTopic.readRecord();
        assertAll(() -> assertEquals(CAMPAIGN_NAME_ABC_LTD,outRec.getValue().getCampaigner())   ,() -> assertEquals(2D,outRec.getValue().getImpressionCount()))  ;
    }

    @Test
    @Order(3)
    @DisplayName("Test the count when user click is sent")
    void sendClickTest(){
        Click userClick = new Click().withImpressionId("100003").withCampaigner(CAMPAIGN_NAME_ABC_LTD);
        TestRecord<String,Click> testRecord= new TestRecord<String, Click>(userClick.getImpressionId(),userClick);
        clickInputTopic.pipeInput(testRecord);
        TestRecord<String,CampaignPerformance> outRec= campaignOutPutTopic.readRecord();
        assertAll(() -> assertEquals(CAMPAIGN_NAME_ABC_LTD,outRec.getValue().getCampaigner())   ,() -> assertEquals(2D,outRec.getValue().getImpressionCount()),() -> assertEquals(1D,outRec.getValue().getClickCount()))  ;
    }


    @Test
    @Order(4)
    @DisplayName("Test state store creation and validate some contents")
    void checkStateStoreTest(){
        KeyValueStore<String,CampaignPerformance> campaignPerformanceKeyValueStore= topologyTestDriver.getKeyValueStore(AppConfig.STORE_NAME_CAMPAIGN_PERFORMANCE);
        CampaignPerformance record= campaignPerformanceKeyValueStore.get(CAMPAIGN_NAME_ABC_LTD);
        assertAll(() -> assertEquals(CAMPAIGN_NAME_ABC_LTD,record.getCampaigner()),
                () -> assertEquals(2D,record.getImpressionCount()),
                ()-> assertEquals(1D,record.getClickCount())
                );
    }



    /**
     * Execute this one to clean everything
     * Close topologyTestDriver
     * Clean the tmp-test directory as well
     */
    @AfterAll
    static void cleanUp(){

        topologyTestDriver.close();
        try {
            Files.delete(Path.of(AppConfig.STATE_STORE_LOCATION_TEST));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}