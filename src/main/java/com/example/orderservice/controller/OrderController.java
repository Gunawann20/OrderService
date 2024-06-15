package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.dtos.OrderDto;
import com.example.orderservice.model.request.OrderRequest;
import com.example.orderservice.model.response.Response;
import com.example.orderservice.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@Validated
public class OrderController {

    private final OrderService orderService;
    private final static String SUCCESS = "success";
    private final static String ERROR = "error";

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Object, Order> createOrder(@Valid @RequestBody OrderRequest request){
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(request.getUserId());
        orderDto.setProductId(request.getProductId());
        orderDto.setQuantity(request.getQuantity());
        Order order = orderService.order(orderDto);

        return new Response<>(SUCCESS, null, order);
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Object, List<Order>> listOrder(){
        List<Order> orders = orderService.listOrder();
        return new Response<>(SUCCESS, null, orders);
    }

    @GetMapping(
            value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<Object, List<Order>> listOrderByUser(@PathVariable Long userId){
        List<Order> orders = orderService.listOrderByUser(userId);
        return new Response<>(SUCCESS, null, orders);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<Map<String, String>, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new Response<>(ERROR, errors, null);
    }
}
