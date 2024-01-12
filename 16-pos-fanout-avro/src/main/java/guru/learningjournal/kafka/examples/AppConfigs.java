package guru.learningjournal.kafka.examples;

public class AppConfigs {
    public final static String CLIENT_ID = "pos-simulator";

    public final static String APPLICATION_ID = "PosFanout";
    public final static String BOOT_STRAP_SERVERS = "localhost:9092";
    public final static String POS_TOPIC_NAME = "pos";
    public final static String SHIPMENT_TOPIC_NAME = "shipment";
    public final static String LOYALTY_TOPIC_NAME = "loyalty";
    public final static String HADOOP_TOPIC_NAME = "hadoop";
    public final static String DELIVERY_TYPE_HOME_DELIVERY = "HOME-DELIVERY";
    public final static String CUSTOMER_TYPE_PRIME = "PRIME";
    public final static Double LOYALTY_FACTOR = 0.02;
}
