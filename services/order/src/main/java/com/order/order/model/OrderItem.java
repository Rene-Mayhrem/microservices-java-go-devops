package com.order.order.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;

public class OrderItem {
    private String productId;
    @Min(1)
    private Integer quantity;
    private BigDecimal price;
}
