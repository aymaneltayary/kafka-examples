package guru.learningjournal.kafka.examples;

class AppConfig {

    public final static String APPLICATION_ID = "advert-ctr-demo";
    public final static String BOOT_STRAP_SERVERS = "localhost:9092";
    public final static String TOPIC_NAME_IMPRESSION = "add-impression";

    public final static String TOPIC_CAMPAIGN_PERFORMANCE = "campaign-performance";
    public final static String TOPIC_NAME_CLICK = "add-click";


    public final static String STATE_STORE_LOCATION_TMP = "tmp/state-store";

    public final static String STATE_STORE_LOCATION_TEST = "tmp-test/state-store";

    public final static String STORE_NAME_CAMPAIGN_PERFORMANCE = "campaign-performance";
}
