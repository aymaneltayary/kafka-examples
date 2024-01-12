package guru.learningjournal.kafka.examples;

class AppConfigs {

    public static final String APPLICATION_ID = "counting-session-app";
    public static final String BOOT_STRAP_SERVERS = "localhost:9092,localhost:9093,localhost:9094";
    final static String TOPIC_NAME_USER_CLICKS = "user-click";
    final static String STATE_STORE_LOCATION = "tmp/state-store";
}
