package com.demo.springboot.kafka.producer.service;

import java.util.List;

import com.demo.springboot.kafka.producer.dto.StockDto;

public interface ProducerService {

  void publish(List<StockDto> stockList);
}
