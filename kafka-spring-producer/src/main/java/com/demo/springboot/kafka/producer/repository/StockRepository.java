package com.demo.springboot.kafka.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.springboot.kafka.producer.repository.model.StockModel;

public interface StockRepository extends JpaRepository<StockModel, Long> {
}
