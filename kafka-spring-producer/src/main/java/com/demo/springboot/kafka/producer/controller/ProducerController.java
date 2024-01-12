package com.demo.springboot.kafka.producer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.kafka.producer.dto.StockDto;
import com.demo.springboot.kafka.producer.repository.StockRepository;
import com.demo.springboot.kafka.producer.service.ProducerService;

@RestController
public class ProducerController {

  @Autowired
  private ProducerService producerSer;

  @Autowired
  private StockRepository stockRepo;

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/stocks")
  public void createStockList(@RequestBody List<StockDto> stockList) {
    producerSer.publish(stockList);
  }
}

