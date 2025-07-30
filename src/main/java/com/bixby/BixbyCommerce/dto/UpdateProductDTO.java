package com.bixby.BixbyCommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
