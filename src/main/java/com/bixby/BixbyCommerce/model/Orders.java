package com.bixby.BixbyCommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderID;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Addresses addresses;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItems> orderItems;



    @PrePersist
    protected void onOrder(){
        this.orderDate = LocalDateTime.now();
    }

}
