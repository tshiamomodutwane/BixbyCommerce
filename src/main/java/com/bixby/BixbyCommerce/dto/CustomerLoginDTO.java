package com.bixby.BixbyCommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerLoginDTO {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}
