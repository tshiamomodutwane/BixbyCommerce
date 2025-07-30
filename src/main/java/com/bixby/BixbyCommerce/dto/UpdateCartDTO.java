package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.CartItems;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateCartDTO {
    private List<CartItems> cartItems;
    private BigDecimal totalAmount = BigDecimal.ZERO;
}
