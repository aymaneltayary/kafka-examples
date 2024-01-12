package learningjournal.kafka.examples;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


@AllArgsConstructor
@Slf4j
public class Dispatcher implements  Runnable{
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
    private String topicName;
    private  String fileName;
    private Producer<Integer,String> producer;

    @Override
    public void run() {
        int counter=1;
        log.info("Starting to read file:{}",fileName);
            Scanner fileScanner = new Scanner(fileName);
            while(fileScanner.hasNextLine()){
                String line= fileScanner.nextLine();
                producer.send(new ProducerRecord<Integer,String>(topicName,counter,line));
                log.info("file:{} , line:{} has been sent",fileName,line);
                counter++;
            }
        log.info("file:{} has been finished.{} lines have been sent.",fileName,counter);
    }


}
