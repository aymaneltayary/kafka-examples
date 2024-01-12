package guru.learningjournal.kafka.examples.topology;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.learningjournal.kafka.examples.AppConfig;

public class HelloTopologyTest {
  private   TopologyTestDriver topologyTestDriver;
  private  TestInputTopic<String,String> inputTopic;

  @BeforeEach
   void  setup(){
    Properties streamProps = new Properties();
    streamProps.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfig.APPLICATION_ID);
    streamProps.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfig.BOOT_STRAP_SERVERS);
    streamProps.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
    streamProps.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);

    topologyTestDriver= new TopologyTestDriver(HelloTopology.build(), streamProps);
    inputTopic=topologyTestDriver.createInputTopic(AppConfig.TOPIC_NAME, Serdes.String().serializer(), Serdes.String().serializer());
  }

  @Test
   void BuildMethodTest(){
    inputTopic.pipeInput("a","aaaa");
    inputTopic.pipeInput("b","bbb");

  }

  @AfterEach
  void  clear(){
    topologyTestDriver.close();
  }

}
