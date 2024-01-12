package guru.learningjournal.kafka.examples;

class AppConfigs {
    final static String APPLICATION_ID = "HelloProducer";
    final static String BOOT_STRAP_SERVERS = "localhost:9092,localhost:9093";
    final static String TOPIC_NAME = "hello-producer-topic";
    final static int NUM_EVENTS = 10000;
}
