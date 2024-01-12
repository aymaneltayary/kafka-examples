package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.PaymentConfirmation;
import guru.learningjournal.kafka.examples.model.PaymentRequest;
import guru.learningjournal.kafka.examples.model.TransactionStatus;
import guru.learningjournal.kafka.examples.serde.AppSerdes;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;

import java.time.Duration;
import java.util.Properties;


@Slf4j
public class KStreamJoinDemo {

    public static void main(String[] args) {
        log.info("KStreamJoinDemo  have been started");
        Properties streamProperties = new Properties();
        streamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.APPLICATION_ID);
        streamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.BOOT_STRAP_SERVERS);
        streamProperties.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.STATE_STORE_LOCATION_TMP);

        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<String, PaymentRequest> payReqStrm = streamsBuilder.stream(AppConfigs.TOPIC_NAME_PAYMENT_REQUEST
                , Consumed.with(AppSerdes.String(), AppSerdes.paymentRequest()).withTimestampExtractor(AppTimestampExtractor.paymentRequest()));

        KStream<String, PaymentConfirmation> payConfStrm = streamsBuilder.stream(AppConfigs.TOPIC_NAME_PAYMENT_CONFIRMATION
                , Consumed.with(AppSerdes.String(), AppSerdes.paymentConfirmation()).withTimestampExtractor(AppTimestampExtractor.paymentConfirmation()));


        payReqStrm
                .join(payConfStrm,
                        (paymentRequest, paymentConfirmation) -> new TransactionStatus().withTransactionId(paymentRequest.getTransactionId()).withStatus((paymentConfirmation.getOtp().equals(paymentRequest.getOtp()) ? "Success" : "Fail"))
                        , JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofMinutes(5)), StreamJoined.with(AppSerdes.String(), AppSerdes.paymentRequest(), AppSerdes.paymentConfirmation()))
                .foreach((key, value) -> log.info("Printing the join output key={}, value={}", key, value));


        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), streamProperties);
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("shut down");
            kafkaStreams.cleanUp();
            kafkaStreams.close();

        }));


    }
}
