package com.demo.springboot.kafka.producer.mapper;

import org.mapstruct.Mapper;

import com.demo.springboot.kafka.producer.dto.StockDto;
import com.demo.springboot.kafka.producer.repository.model.StockModel;

@Mapper(componentModel = "spring")
public interface StockMapper {

  StockDto StockModelToStockDto(StockModel source);

  StockModel stockDtoToStockModel(StockDto source);
}
