package guru.learningjournal.kafka.examples;

class AppConfigs {

    public final static String APPLICATION_ID = "kstream-join-demo";
    public final static String BOOT_STRAP_SERVERS = "localhost:9092";
    public final static String TOPIC_NAME_PAYMENT_REQUEST = "payment-request";
    public final static String TOPIC_NAME_PAYMENT_CONFIRMATION = "payment-confirmation";
    public final static String STATE_STORE_LOCATION_TMP = "tmp/state-store";
}
