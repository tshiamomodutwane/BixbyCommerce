package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.AddressType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAddressDTO {
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @NotBlank
    private String zipCode;
    @NotBlank
    private AddressType type;
}
