package com.example.orderservice.model.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {

    @NotNull(message = "userId cannot null")
    private Long userId;
    @NotNull(message = "productId cannot null")
    private Long productId;
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be equal to or greater than 1")
    private Integer quantity;
}
