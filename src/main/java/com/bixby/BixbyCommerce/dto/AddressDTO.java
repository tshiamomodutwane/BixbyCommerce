package com.bixby.BixbyCommerce.dto;

import com.bixby.BixbyCommerce.model.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long customerId;
    private String street;
    private String city;
    private String zipCode;
    private AddressType type;
}
