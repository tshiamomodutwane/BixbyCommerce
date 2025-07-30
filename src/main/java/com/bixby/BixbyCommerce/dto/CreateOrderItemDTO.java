package com.bixby.BixbyCommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderItemDTO {
    @NotNull(message = "Product Id is required")
    private Long productId;
    private int quantity;
}
