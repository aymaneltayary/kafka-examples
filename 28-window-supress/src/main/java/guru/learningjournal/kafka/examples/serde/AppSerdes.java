package guru.learningjournal.kafka.examples.serde;

import guru.learningjournal.kafka.examples.model.HeartBeat;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {

    public static Serde<HeartBeat> HeartBeat() {
        HeartBeatSerde serde = new HeartBeatSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, HeartBeat.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class HeartBeatSerde extends WrapperSerde<HeartBeat> {
        HeartBeatSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

}
