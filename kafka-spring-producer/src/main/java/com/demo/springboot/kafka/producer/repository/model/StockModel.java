package com.demo.springboot.kafka.producer.repository.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stock")
@Getter
@Setter
public class StockModel {

  @Id
  @Column(name = "id", updatable = false)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "current_price")
  private BigDecimal currentPrice;

  @UpdateTimestamp
  @Column(name = "last_update")
  private LocalDateTime lastUpdate;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StockModel that)) return false;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(currentPrice, that.currentPrice) && Objects.equals(lastUpdate, that.lastUpdate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, currentPrice, lastUpdate);
  }
}
