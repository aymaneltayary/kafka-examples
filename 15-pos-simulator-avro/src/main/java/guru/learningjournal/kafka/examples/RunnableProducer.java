package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.datagenerator.InvoiceGenerator;
import guru.learningjournal.kafka.examples.model.PosInvoice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

@Slf4j
@AllArgsConstructor
public class RunnableProducer implements Runnable{

   private int threadNumber;

   private String color;

   private int produceSpeed;

   private String topicName;

   private Producer<String, PosInvoice> kafkaProducer;





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
        log.info(color+"Thread #{} has been started. ",threadNumber);
    for (int i=0 ;i< 10000;i++){
        PosInvoice invoice= InvoiceGenerator.getInstance().getNextInvoice();

        kafkaProducer.send(new ProducerRecord<>(topicName,invoice.getInvoiceNumber().toString(),invoice));

        log.info(color+"Thread #{}  invoice has been sent with invoiceNumber={}",threadNumber ,invoice.getInvoiceNumber());
        try {
            Thread.sleep(produceSpeed);
        } catch (InterruptedException e) {
            log.error(color+"Something went wrong {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }
        log.info(color+"Thread #{} finished. ",threadNumber);
    }
}
