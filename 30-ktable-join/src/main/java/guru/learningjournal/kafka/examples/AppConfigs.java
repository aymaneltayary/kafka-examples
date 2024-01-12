package guru.learningjournal.kafka.examples;

class AppConfigs {

    public final static String APPLICATION_ID = "ktable-join-demo";
    public final static String BOOT_STRAP_SERVERS = "localhost:9092,localhost:9093";
    public final static String TOPIC_NAME_USER_MASTER = "user-master";
    public final static String TOPIC_NAME_USER_LOGIN = "user-login";
    public final static String STATE_STORE_LOCATION_TMP = "tmp/state-store";
}
