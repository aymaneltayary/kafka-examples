package guru.learningjournal.kafka.examples;

public class AppConfig {
    public final static String APPLICATION_ID = "rewards-app";
    public final static String BOOT_STRAP_SERVERS = "localhost:9092";
    public final static String POS_TOPIC_NAME = "pos";

    public final static String NOTIFICATION_TOPIC_NAME = "loyalty";
    public final static String CUSTOMER_TYPE_PRIME = "PRIME";
    public final static Double LOYALTY_FACTOR = 0.02;
    public final static String REWARDS_STORE_NAME = "customer-rewards-store";
    public final static String REWARDS_TEMP_TOPIC = "pos-temp";
}
