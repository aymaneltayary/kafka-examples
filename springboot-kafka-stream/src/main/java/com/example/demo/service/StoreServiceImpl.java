package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.stereotype.Service;

import com.example.demo.conf.OrderStreamConfiguration;
import com.example.demo.domain.OrderCount;

@Service
public class StoreServiceImpl implements StoreService {

  private final static String STATE_STORE_LOCATION_TMP = "tmp/state-store";

  @Autowired
  StreamsBuilderFactoryBean streamsBuilderFactoryBean;

  public List<OrderCount> getAllOrderCount() {
    ReadOnlyKeyValueStore<String, Long> storeCountList = streamsBuilderFactoryBean.getKafkaStreams()
      .store(StoreQueryParameters.fromNameAndType(OrderStreamConfiguration.STATE_STORE_NAME_ORDER_COUNT, QueryableStoreTypes.keyValueStore()));
    List<OrderCount> resultAsList = new ArrayList<>();
    storeCountList.all().forEachRemaining(orderLongKeyValue -> {
      OrderCount orderCount = new OrderCount(orderLongKeyValue.key, orderLongKeyValue.value);
      resultAsList.add(orderCount);
    });
    return resultAsList;
  }
}
