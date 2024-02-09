package com.example.demo.domain;

import lombok.Data;

@Data
public class TotalRevenueWithAddress {
    private TotalRevenue totalRevenue;
    private Store store;
}
