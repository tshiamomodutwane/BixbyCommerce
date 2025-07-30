package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.Products;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class SellerDTO {
    private Long sellerID;
    private String businessName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String businessType;
    private String companyRegistration;
    private String productCategories;
    private String bankingInfo;
    private String address;
    private String profileImage;
    private String vatNumber;
    private List<Products> products;
}
