package com.example.demo.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.streams.errors.InvalidStateStoreException;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.domain.OrderLineItem;
import com.example.demo.domain.OrderType;
import com.example.demo.domain.PosInvoice;

@SpringBootTest

@EmbeddedKafka(topics = "order")
@TestPropertySource(properties = {
  "spring.kafka.streams.bootstrap-servers=${spring.embedded.kafka.brokers}",
  "spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
  "spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer",
  "spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.IntegerSerializer"
})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class ServiceDemoImplTest {

  public static final String LOC_1 = "loc1";
  public static final String LOC_2 = "loc2";
  private final String TOPIC_NAME_ORDER = "order";
  @Autowired
  private StoreService storeService;
  @Autowired
  private StreamsBuilderFactoryBean streamsBuilderFactoryBean;
  @Autowired
  private KafkaTemplate<Integer, PosInvoice> kafkaTemplate;

  @Test
  @Order(1)
  void testGetAllOrderCount() {
    // given
    publishOrders();
    System.out.println("publishOrders");
    Awaitility.await().atMost(30, TimeUnit.SECONDS).pollDelay(Duration.ofSeconds(10))
      // ignore the exception in case of KAFKA still not running
      .ignoreException(InvalidStateStoreException.class)
      .until(() -> storeService.getAllOrderCount(), orderCounts -> orderCounts.size() == 2);
    Assertions.assertEquals(2, storeService.getAllOrderCount().size());

    Assertions.assertEquals(2, storeService.getAllOrderCount().stream().filter(
      orderCount -> orderCount.locationId().equals(LOC_1)
    ).findFirst().get().count());
    Assertions.assertEquals(1, storeService.getAllOrderCount().stream().filter(
      orderCount -> orderCount.locationId().equals(LOC_2)
    ).findFirst().get().count());
  }

  /**
   * send testing data for Kafka
   */

  private boolean publishOrders() {

    //kafkaTemplate.
    List<OrderLineItem> itemList = new ArrayList<>();
    OrderLineItem item = new OrderLineItem("item1", 1, new BigDecimal(100));
    itemList.add(item);
    item = new OrderLineItem("item2", 2, new BigDecimal(200));
    itemList.add(item);

    PosInvoice order = new PosInvoice(1, "loc1", new BigDecimal(300), OrderType.GENERAL, itemList, LocalDateTime.now());
    kafkaTemplate.send(TOPIC_NAME_ORDER, order.orderId(), order);
    order = new PosInvoice(2, "loc1", new BigDecimal(500), OrderType.GENERAL, itemList, LocalDateTime.now());
    kafkaTemplate.send(TOPIC_NAME_ORDER, order.orderId(), order);

    itemList = new ArrayList<>();
    item = new OrderLineItem("item2", 2, new BigDecimal(200));
    itemList.add(item);
    order = new PosInvoice(3, "loc2", new BigDecimal(200), OrderType.GENERAL, itemList, LocalDateTime.now());
    kafkaTemplate.send(TOPIC_NAME_ORDER, order.orderId(), order);
    return true;
  }

  @AfterEach
  public void destroy() {
    //First  shutdown the stream
    Objects.requireNonNull(streamsBuilderFactoryBean.getKafkaStreams()).close();
    // then clean up the local state store dir.
    streamsBuilderFactoryBean.getKafkaStreams().cleanUp();
  }
}
