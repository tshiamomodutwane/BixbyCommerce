package com.bixby.BixbyCommerce.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdminDTO {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
}
