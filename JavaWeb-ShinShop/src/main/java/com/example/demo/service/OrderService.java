package com.example.demo.service;

import com.example.demo.model.dto.OrderItemDto;
import com.example.demo.model.dto.PlaceOrderDto;
import com.example.demo.model.po.Order;
import com.example.demo.model.po.OrderDetail;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderDetailRepository orderDetailRepo;
    @Autowired private UserRepository userRepository;
    @Autowired private ItemRepository itemRepository;

    // 下單流程，主要搬自原 controller
    public ResponseEntity<?> placeOrder(PlaceOrderDto order, Integer userId) {
        if(userId == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "請先登入"));

        Order main = new Order();
        main.setUser(userRepository.findById(userId).orElse(null));
        main.setPickupType(order.getPickupType());
        main.setPaymentType(order.getPaymentType());
        main.setRemark(order.getRemark());
        int total = order.getItems().stream().mapToInt(i -> i.getQuantity()*i.getUnitPrice()).sum();
        main.setTotalAmount(total);
        main.setOrderStatus("未完成");
        orderRepo.save(main);

        for(OrderItemDto i : order.getItems()) {
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
