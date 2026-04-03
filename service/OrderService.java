package com.example.Ecommerce.service;

import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.entity.OrderItem;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.exception.ProductOutOfStock;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.repository.OrderRepo;
import com.example.Ecommerce.repository.ProductRepo;
import com.example.Ecommerce.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.ScopedValue;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private  UserRepo userRepository;
    @Autowired
    private  ProductRepo productRepository;
    @Autowired
    private  OrderRepo orderRepository;


    public Order placeOrder(Long userId, OrderRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("CREATED");

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (OrderRequest.OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            if (product.getStock() < itemRequest.getQuantity()) {
                throw new ProductOutOfStock((
                        "Not enough stock for product: " + product.getName()
                );
            }

            product.setStock(product.getStock() - itemRequest.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setItemPrice(product.getPrice());

            orderItems.add(orderItem);

            totalAmount += product.getPrice() * itemRequest.getQuantity();
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    public void cancelOrder(Long userId, Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!order.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("You cannot cancel this order");
        }

        order.setStatus("CANCELLED");

        orderRepository.save(order);
    }

    public Optional findById(Long orderId) {
      return  orderRepository.findById(orderId);
    }
}

