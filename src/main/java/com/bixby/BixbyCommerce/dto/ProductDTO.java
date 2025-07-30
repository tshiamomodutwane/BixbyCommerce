package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.Category;
import com.bixby.BixbyCommerce.model.Sellers;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductDTO {
    private Long productID;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private Long categoryID;
    private String categoryName;
    private String imageURL;

}
