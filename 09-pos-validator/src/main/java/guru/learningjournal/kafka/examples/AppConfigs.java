package guru.learningjournal.kafka.examples;

public class AppConfigs {
    public final static String APPLICATION_ID= "PosValidator";
    public final static String BOOTSTRAP_SERVERS = "localhost:9092";
    public final static String GROUP_ID = "PosValidatorGroup";
    public final static String[] SOURCE_TOPIC_NAMES = {"pos"};
    public final static String VALID_TOPIC_NAME = "valid-pos";
    public final static String INVALID_TOPIC_NAME = "invalid-pos";
}
