package com.bixby.BixbyCommerce.dto;

import lombok.Data;

import java.util.List;
@Data
public class UpdateOrderDTO {
    private Long customerId;
    private List<CreateOrderItemDTO> orderItems;
    private String address;
}
