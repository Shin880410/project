package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.dto.OrderDto.PlaceOrderDto;
import com.example.demo.model.po.Order;
import com.example.demo.model.po.OrderDetail;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.securityUtil.SessionUtil;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {
	@Autowired private OrderRepository orderRepo;
    @Autowired private OrderDetailRepository orderDetailRepo;
    @Autowired private UserRepository userRepository;
    @Autowired private ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDto order, HttpSession session) {
        Integer userId = SessionUtil.getUserId(session);
        if(userId == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "請先登入"));

        // 實作下單邏輯
        Order main = new Order();
        main.setUser(userRepository.findById(userId).orElse(null));
        main.setPickupType(order.getPickupType());
        main.setPaymentType(order.getPaymentType());
        main.setRemark(order.getRemark());
        int total = order.getItems().stream().mapToInt(i -> i.getQuantity()*i.getUnitPrice()).sum();
        main.setTotalAmount(total);
        main.setOrderStatus("未完成");
        orderRepo.save(main);

        for(PlaceOrderDto.OrderItemDto i : order.getItems()) {
            OrderDetail d = new OrderDetail();
            d.setOrder(main);
            d.setItem(itemRepository.findById(i.getItemId()).orElse(null));
            d.setOptions(i.getOptions());
            d.setQuantity(i.getQuantity());
            d.setUnitPrice(i.getUnitPrice());
            d.setSubtotal(i.getQuantity() * i.getUnitPrice());
            orderDetailRepo.save(d);
        }
        return ResponseEntity.ok(Map.of("result","OK","orderId", main.getOrderId()));
    }
}


