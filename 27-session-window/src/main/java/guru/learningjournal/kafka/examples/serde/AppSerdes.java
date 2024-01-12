package guru.learningjournal.kafka.examples.serde;

import guru.learningjournal.kafka.examples.model.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {

    static final class PosInvoiceSerde extends WrapperSerde<UserClick> {
        PosInvoiceSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<UserClick> UserClick() {
        PosInvoiceSerde serde = new PosInvoiceSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, UserClick.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

}
