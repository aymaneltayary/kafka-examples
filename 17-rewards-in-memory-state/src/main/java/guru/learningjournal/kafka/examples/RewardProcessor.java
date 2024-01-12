package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.Notification;
import guru.learningjournal.kafka.examples.model.PosInvoice;
import org.apache.kafka.streams.processor.api.FixedKeyProcessor;
import org.apache.kafka.streams.processor.api.FixedKeyProcessorContext;
import org.apache.kafka.streams.processor.api.FixedKeyRecord;

public class RewardProcessor implements FixedKeyProcessor<String, PosInvoice, Notification> {


    @Override
    public void init(FixedKeyProcessorContext<String, Notification> context) {
        FixedKeyProcessor.super.init(context);
    }

    @Override
    public void process(FixedKeyRecord<String, PosInvoice> fixedKeyRecord) {

    }

    @Override
    public void close() {
        FixedKeyProcessor.super.close();
    }
}
