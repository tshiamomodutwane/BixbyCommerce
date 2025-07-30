package com.bixby.BixbyCommerce.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateCartItemDTO {
    @NotNull(message = "Product ID is required")
    private Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}
