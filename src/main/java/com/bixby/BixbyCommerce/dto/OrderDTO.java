package com.bixby.BixbyCommerce.dto;



import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;
    private Long customerId;
    private String shippingAddress;
    private String paymentMethod;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private String status;
    private List<OrderItemDTO> orderItems;
}
