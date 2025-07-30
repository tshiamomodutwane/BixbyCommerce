package com.bixby.BixbyCommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long orderItemId;
    private Long productId;
    private Long customerId;
    private String productName;
    private int quantity;
    private BigDecimal price;
}
