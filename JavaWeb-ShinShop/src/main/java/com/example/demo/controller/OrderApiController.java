package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.PlaceOrderDto;
import com.example.demo.service.OrderService;
import com.example.demo.util.SessionUtil;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
	@Autowired private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDto order, HttpSession session) {
        Integer userId = SessionUtil.getUserId(session);
        return orderService.placeOrder(order, userId);
    }
}


