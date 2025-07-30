package com.bixby.BixbyCommerce.dto;

import lombok.Data;

import java.util.List;
@Data
public class CreateOrderDTO {
    private Long customerId;
    private Long productId;
    private List<CreateOrderItemDTO> orderItems;
    private String address;
}
