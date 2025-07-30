package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.Products;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryDTO {
    private Long categoryID;
    private String name;
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();
    private List<ProductDTO> products;
}
