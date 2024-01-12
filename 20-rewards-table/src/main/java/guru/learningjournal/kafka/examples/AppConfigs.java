package guru.learningjournal.kafka.examples;

public class AppConfigs {
    public  static final String APPLICATION_ID = "rewards-app";
    public static final String BOOT_STRAP_SERVERS = "localhost:9092";
    public static final String POS_TOPIC_NAME = "pos";

    public static final String NOTIFICATION_TOPIC_NAME = "loyalty";
    public static final String CUSTOMER_TYPE_PRIME = "PRIME";
    public static final Double LOYALTY_FACTOR = 0.02;

    public static final Double INITIAL_TOTAL_LOYALTY_POINTS_ZERO=0D;

}
