package com.example.demo.topology;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import com.example.demo.conf.OrderStreamConfiguration;
import com.example.demo.domain.PosInvoice;
import com.example.demo.serde.CustomSerde;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderTopology {

  private final String TOPIC_NAME_ORDER = "order";

  /**
   * Autowire streamsBuilder the is created by the
   * StreamsBuilderFactoryBean
   *
   * @param streamsBuilder
   */

  @Autowired
  public void buildCount(StreamsBuilder streamsBuilder) {
    streamsBuilder.stream(TOPIC_NAME_ORDER, Consumed.with(CustomSerde.String(), new JsonSerde<PosInvoice>(PosInvoice.class)))
      .groupBy((s, order) -> order.locationId())
      .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as(OrderStreamConfiguration.STATE_STORE_NAME_ORDER_COUNT).withKeySerde(Serdes.String()).withValueSerde(Serdes.Long()));
  }
}
