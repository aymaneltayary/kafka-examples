package com.demo.springboot.kafka.producer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.springboot.kafka.producer.dto.StockDto;
import com.demo.springboot.kafka.producer.mapper.StockMapper;
import com.demo.springboot.kafka.producer.repository.StockRepository;
import com.demo.springboot.kafka.producer.service.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

  @Autowired
  private KafkaTemplate<Long, StockDto> kafkaTemplate;

  @Autowired
  private StockMapper stockMapper;

  @Autowired
  private StockRepository stockRepository;

  public void publish(List<StockDto> stockList) {
    stockList.forEach(dto -> kafkaTemplate.send("topic1", dto.getId(), dto));
  }
}
