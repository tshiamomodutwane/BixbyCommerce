package com.bixby.BixbyCommerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartDTO {
    private Long cartID;
    private Long customerID;
    private LocalDateTime createdAt;
    private List<CartItemDTO> cartItems;
    private BigDecimal totalAmount = BigDecimal.ZERO;
}
