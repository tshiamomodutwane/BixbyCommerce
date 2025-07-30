package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.AddressType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAddressDTO {
    private String street;
    private String city;
    private String zipCode;
    private AddressType type;
}
