package com.example.orderservice.controllers;

import com.example.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
}
