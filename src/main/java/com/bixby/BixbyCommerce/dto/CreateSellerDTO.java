package com.bixby.BixbyCommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSellerDTO {
    @NotBlank(message = "Business Name is Required")
    private String businessName;
    @NotBlank(message = "Full Name is Required")
    private String fullName;
    @NotBlank(message = "Valid email is Required")
    @Email
    private String email;
    @NotBlank(message = "Phone number is Required")
    private String phoneNumber;
    @NotBlank(message = "Strong password is Required")
    private String password;
    @NotBlank(message = "Business Type is Required")
    private String businessType;
    @NotBlank(message = "Company Registration is Required")
    private String companyRegistration;
    private String productCategories;
    @NotBlank(message = " Banking info is Required")
    private String bankingInfo;
    @NotBlank(message = "Address is Required")
    private String address;
    private String profileImage;
    @NotBlank(message = "VAT number is Required")
    private String vatNumber;
}
