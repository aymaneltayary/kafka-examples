package guru.learningjournal.kafka.examples.serde;

import guru.learningjournal.kafka.examples.model.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {

    static final class AdImpressionSerde extends WrapperSerde<Impression> {
        AdImpressionSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Impression> impression() {
        AdImpressionSerde serde = new AdImpressionSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Impression.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class AdClickSerde extends WrapperSerde<Click> {
        AdClickSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Click> click() {
        AdClickSerde serde = new AdClickSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Click.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class CampaignPerformanceSerde extends WrapperSerde<CampaignPerformance> {
        CampaignPerformanceSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<CampaignPerformance> CampaignPerformance() {
        CampaignPerformanceSerde serde = new CampaignPerformanceSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, CampaignPerformance.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

}
