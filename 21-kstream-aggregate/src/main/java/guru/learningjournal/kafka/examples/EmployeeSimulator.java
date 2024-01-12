package guru.learningjournal.kafka.examples;

import guru.learningjournal.kafka.examples.model.Employee;
import guru.learningjournal.kafka.examples.serde.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

@Slf4j
public class EmployeeSimulator {
    public static void main(String[] args) {
        log.info("EmployeeSimulator has been started");
        Properties producerProperties= new Properties();
        producerProperties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.APPLICATION_ID);
        producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.BOOT_STRAP_SERVERS);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


      try(KafkaProducer<String,Employee> kafkaProducer= new KafkaProducer<>(producerProperties)){

          for(int i =0;i<10;i++){
              Employee emp= new Employee();
              emp.setId(UUID.randomUUID().toString());
              emp.setName("emp-name"+i);
              if(i<3){
                  emp.setDepartment("HR");
                  emp.setSalary(100);
              }else if(i>3&&i<7){
                  emp.setDepartment("IT");
                  emp.setSalary(200);
              }else{
                  emp.setDepartment("FINANCE");
                  emp.setSalary(50);
              }

              ProducerRecord<String,Employee> empRecord= new ProducerRecord<>(AppConfigs.TOPIC_NAME_EMPLOYEE,emp.getId(), emp);
              kafkaProducer.send(empRecord);
              log.info("Employee={} has been sent to topic={}",emp,AppConfigs.TOPIC_NAME_EMPLOYEE);

          }

      }

        log.info("EmployeeSimulator finished");





    }

}
