package guru.learningjournal.kafka.examples.topology;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;

import guru.learningjournal.kafka.examples.AppConfig;

public class HelloTopology {


  private static final StreamsBuilder  StreamsBuilder= new StreamsBuilder();

  private HelloTopology(){

  }

  public static Topology build(){
    StreamsBuilder streamsBuilder= new StreamsBuilder();
    KStream<String, String> kstream= streamsBuilder.stream(AppConfig.TOPIC_NAME);
    kstream.print(Printed.<String,String> toSysOut().withLabel("message"));
    return streamsBuilder.build();
  }


}
