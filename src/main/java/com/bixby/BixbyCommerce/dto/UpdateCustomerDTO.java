package com.bixby.BixbyCommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCustomerDTO {
    private String name;

    @Email
    private String email;
    @Size(min = 6)
    private String password;
}
