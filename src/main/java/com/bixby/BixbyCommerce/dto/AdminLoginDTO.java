package com.bixby.BixbyCommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class AdminLoginDTO {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
}
