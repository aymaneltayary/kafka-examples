package guru.learningjournal.kafka.examples;

public class AppConfig {
   public final static String APPLICATION_ID = "HelloStreams";
    public final static String PRODUCER_APPLICATION_ID = "HelloProducer";
    public final static String BOOT_STRAP_SERVERS = "localhost:9092";
    public final static String TOPIC_NAME = "hello-producer-topic";
    public  final static int NUM_EVENTS = 100000;
}
