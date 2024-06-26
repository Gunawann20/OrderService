package com.example.orderservice.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {

    private Long userId;
    private Long productId;
    private Integer quantity;
}
