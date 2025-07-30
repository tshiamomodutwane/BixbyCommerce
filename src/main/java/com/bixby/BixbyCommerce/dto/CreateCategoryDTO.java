package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.Products;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateCategoryDTO {
    private String name;
    private String description;
}
