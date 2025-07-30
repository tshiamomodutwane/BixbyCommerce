package com.bixby.BixbyCommerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateCartDTO {
    private List<CartItemDTO> cartItems;
    private BigDecimal totalAmount = BigDecimal.ZERO;
}
