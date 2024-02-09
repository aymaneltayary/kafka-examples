package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.OrderCount;
import com.example.demo.service.StoreService;

@RestController
public class StoreController {

  @Autowired
  StoreService srv;

  @GetMapping("/orders")
  public List<OrderCount> getAllOrderCount() {
    return srv.getAllOrderCount();
  }
}
