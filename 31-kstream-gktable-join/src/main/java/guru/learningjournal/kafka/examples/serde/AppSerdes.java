package guru.learningjournal.kafka.examples.serde;


import guru.learningjournal.kafka.examples.aggregate.model.TopThreeCount;
import guru.learningjournal.kafka.examples.model.*;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {

    static final class InventorySerde extends WrapperSerde<Inventory> {
        InventorySerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<Inventory> Inventory() {
        InventorySerde serde = new InventorySerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Inventory.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class UserClickSerde extends WrapperSerde<UserClick> {
        UserClickSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<UserClick> userClick() {
        UserClickSerde serde = new UserClickSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, UserClick.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }


    static final class TypeCountSerde extends WrapperSerde<TypeCount> {
        TypeCountSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<TypeCount> typeCount() {
        TypeCountSerde serde = new TypeCountSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, TypeCount.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }



    static final class TopThreeCountSerde extends WrapperSerde<TopThreeCount> {
        TopThreeCountSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    public static Serde<TopThreeCount> topThreeCount() {
        TopThreeCountSerde serde = new TopThreeCountSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, TopThreeCount.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }



}
