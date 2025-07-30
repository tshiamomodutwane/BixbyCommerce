package com.bixby.BixbyCommerce.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateProductDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String categoryName;
    private String imageURL;
}
