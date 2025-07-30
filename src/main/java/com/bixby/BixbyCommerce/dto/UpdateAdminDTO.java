package com.bixby.BixbyCommerce.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAdminDTO {
    private String name;
    @Email
    private String email;
    @Size(max = 10)
    private String phoneNumber;
    @Size(min = 6)
    private String password;
}
