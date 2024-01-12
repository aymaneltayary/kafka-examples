package guru.learningjournal.kafka.examples;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import guru.learningjournal.kafka.examples.model.*;

import java.util.List;

//@AllArgsConstructor
@Slf4j
@AllArgsConstructor
public class RunnableProducer implements Runnable{

    private int threadNo;

    private Producer<String, PosInvoice> producer;

    private List<PosInvoice> invoiceList;

    private String topicName;

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

      log.info("Thread #{} started to send invoice list of size={}",threadNo,invoiceList.size());
        invoiceList.forEach(invoice->{
            producer.send(new ProducerRecord<>(topicName,invoice.getStoreId(),invoice));
        });
       log.info("Thread #{} finished. All invoice have been sent successfully",threadNo);

    }
}
