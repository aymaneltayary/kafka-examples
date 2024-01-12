package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.PosInvoice;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class InvoiceTimeStampExtractor implements TimestampExtractor {
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SS'Z'";

    /**
     * @param consumerRecord
     * @param prevTime       the time of the previous message
     * @return
     */
    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        PosInvoice invoice = (PosInvoice) consumerRecord.value();
        LocalDateTime invoiceDate = LocalDateTime.parse(invoice.getCreatedTime(), DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
        long eventTime = invoiceDate.toInstant(ZoneOffset.UTC).toEpochMilli();
        return ((eventTime > 0) ? eventTime : prevTime);
    }
}
