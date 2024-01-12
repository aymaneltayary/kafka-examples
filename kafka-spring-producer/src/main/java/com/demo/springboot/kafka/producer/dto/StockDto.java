package com.demo.springboot.kafka.producer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StockDto {

  private Long id;
  private String name;

  private BigDecimal currentPrice;

  private LocalDateTime lastUpdate;
}
