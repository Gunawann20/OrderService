package com.example.orderservice.services.impl;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.dtos.OrderDto;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.services.OrderService;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Order order(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setProductId(orderDto.getProductId());
        order.setQuantity(orderDto.getQuantity());
        order.setDate(new Date());

        return orderRepository.save(order);
    }

    @Override
    public List<Order> listOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> listOrderByUser(Long userId) {
        return orderRepository.findOrderByUserId(userId);
    }
}
