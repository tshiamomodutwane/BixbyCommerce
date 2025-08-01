package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.AddressType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAddressDTO {
    private Long customerId;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @NotBlank
    private AddressType type;
}
