package com.bixby.BixbyCommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private Long cartItemID;
    private Long productID;
    private String productName;
    private String imageURL;
    private int quantity;
    private BigDecimal price;
}
