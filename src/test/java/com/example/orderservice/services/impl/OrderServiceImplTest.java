package com.example.orderservice.services.impl;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.dtos.OrderDto;
import com.example.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void order() {

        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(1L);
        orderDto.setProductId(2L);
        orderDto.setQuantity(3);

        Order order = new Order();
        order.setUserId(1L);
        order.setProductId(2L);
        order.setQuantity(3);
        order.setDate(new Date());

        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order result = orderService.order(orderDto);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        assertEquals(2L, result.getProductId());
        assertEquals(3, result.getQuantity());
        Mockito.verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void listOrder() {

        Order order1 = new Order();
        order1.setUserId(1L);
        order1.setProductId(2L);
        order1.setQuantity(3);
        order1.setDate(new Date());

        Order order2 = new Order();
        order2.setUserId(4L);
        order2.setProductId(5L);
        order2.setQuantity(6);
        order2.setDate(new Date());

        List<Order> orders = Arrays.asList(order1, order2);

        Mockito.when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.listOrder();

        assertNotNull(result);
        assertEquals(2, result.size());
        Mockito.verify(orderRepository, times(1)).findAll();
    }

    @Test
    void listOrderByUser() {

        Long userId = 1L;
        Order order1 = new Order();
        order1.setUserId(userId);
        order1.setProductId(2L);
        order1.setQuantity(3);
        order1.setDate(new Date());

        Order order2 = new Order();
        order2.setUserId(userId);
        order2.setProductId(5L);
        order2.setQuantity(6);
        order2.setDate(new Date());

        List<Order> orders = Arrays.asList(order1, order2);

        Mockito.when(orderRepository.findOrderByUserId(userId)).thenReturn(orders);

        List<Order> result = orderService.listOrderByUser(userId);

        assertNotNull(result);
        assertEquals(2, result.size());
        Mockito.verify(orderRepository, times(1)).findOrderByUserId(userId);
    }
}