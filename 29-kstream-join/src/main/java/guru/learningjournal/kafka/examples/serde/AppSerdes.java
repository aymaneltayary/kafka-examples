package guru.learningjournal.kafka.examples.serde;

import guru.learningjournal.kafka.examples.model.PaymentConfirmation;
import guru.learningjournal.kafka.examples.model.PaymentRequest;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

import java.util.HashMap;
import java.util.Map;

public class AppSerdes extends Serdes {

    public static Serde<PaymentRequest> paymentRequest() {
        PaymentRequestSerde serde = new PaymentRequestSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, PaymentRequest.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    public static Serde<PaymentConfirmation> paymentConfirmation() {
        PaymentConfirmationSerde serde = new PaymentConfirmationSerde();

        Map<String, Object> serdeConfigs = new HashMap<>();
        serdeConfigs.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, PaymentConfirmation.class);
        serde.configure(serdeConfigs, false);

        return serde;
    }

    static final class PaymentRequestSerde extends WrapperSerde<PaymentRequest> {
        PaymentRequestSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

    static final class PaymentConfirmationSerde extends WrapperSerde<PaymentConfirmation> {
        PaymentConfirmationSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>());
        }
    }

}
