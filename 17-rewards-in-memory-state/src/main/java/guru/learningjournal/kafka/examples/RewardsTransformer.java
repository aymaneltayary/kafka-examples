package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.*;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

@SuppressWarnings("unchecked")
public class RewardsTransformer implements ValueTransformer<PosInvoice, Notification> {
    private KeyValueStore<String, Double> stateStore;

    @Override
    public void init(ProcessorContext processorContext) {
        this.stateStore = (KeyValueStore<String, Double>) processorContext.getStateStore(AppConfig.REWARDS_STORE_NAME);
    }

    @Override
    public Notification transform(PosInvoice posInvoice) {
        Notification notification = new Notification()
            .withInvoiceNumber(posInvoice.getInvoiceNumber())
            .withCustomerCardNo(posInvoice.getCustomerCardNo())
            .withTotalAmount(posInvoice.getTotalAmount())
            .withEarnedLoyaltyPoints(posInvoice.getTotalAmount() * AppConfig.LOYALTY_FACTOR)
            .withTotalLoyaltyPoints(0.0);
        Double accumulatedRewards = stateStore.get(notification.getCustomerCardNo());
        Double totalRewards;
        if (accumulatedRewards != null)
            totalRewards = accumulatedRewards + notification.getEarnedLoyaltyPoints();
        else
            totalRewards = notification.getEarnedLoyaltyPoints();
        stateStore.put(notification.getCustomerCardNo(), totalRewards);
        notification.setTotalLoyaltyPoints(totalRewards);

        return notification;
    }

    @Override
    public void close() {

    }
}
