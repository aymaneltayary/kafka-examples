package com.example.demo.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TotalRevenue {
    private String locationId;
    private long orderCount = 0;
    private BigDecimal revenue = BigDecimal.ZERO;
}
