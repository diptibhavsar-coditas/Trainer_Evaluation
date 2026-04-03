package com.example.Ecommerce.controller;

import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private  OrderService orderService;

    @PostMapping("/place")
    public <OrderRequest> ResponseEntity<Order> placeOrder(@RequestParam Long userId,
                                                           @RequestBody OrderRequest request) {

        Order savedOrder = orderService.placeOrder(userId, request);

        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PutMapping("/cancel")
    public <CancelOrderRequest> ResponseEntity<String> cancelOrder(@RequestBody CancelOrderRequest request) {

        orderService.cancelOrder(request.getUserId(), request.getOrderId());

        return ResponseEntity.ok("Order cancelled successfully");
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {

        Order order = orderService.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        return ResponseEntity.ok(order);
    }
}

