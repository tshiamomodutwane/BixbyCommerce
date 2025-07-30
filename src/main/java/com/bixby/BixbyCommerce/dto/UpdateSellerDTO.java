package com.bixby.BixbyCommerce.dto;

import lombok.Data;

@Data
public class UpdateSellerDTO {
    private String businessName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String businessType;
    private String companyRegistration;
    private String productCategories;
    private String bankingInfo;
    private String address;
    private String profileImage;
    private String vatNumber;
}
