package com.artemis.demo.controller;

import com.artemis.demo.model.Orders;
import com.artemis.demo.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Place Order
    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Orders> checkout(@PathVariable Long userId) {
        Orders order = orderService.placeOrder(userId);
        return ResponseEntity.ok(order);
    }

    // Get User Orders
    @GetMapping("/{userId}")
    public ResponseEntity<List<Orders>> getUserOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getUserOrders(userId));
    }
}
