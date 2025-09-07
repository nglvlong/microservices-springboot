package com.example.orderservice.services.impl;

import com.example.orderservice.repositories.OrderRepository;
import com.example.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
}
