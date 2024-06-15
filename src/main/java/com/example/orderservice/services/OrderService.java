package com.example.orderservice.services;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.dtos.OrderDto;

import java.util.List;

public interface OrderService {

    Order order(OrderDto orderDto);
    List<Order> listOrder();
    List<Order> listOrderByUser(Long userId);
}
