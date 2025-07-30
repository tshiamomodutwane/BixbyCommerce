package com.bixby.BixbyCommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressID;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customers;

    private String street;
    private String city;
    @Column(name = "zip_code")
    private String zipCode;

    @Enumerated(EnumType.STRING)
    private AddressType type;

    @OneToMany(mappedBy = "addresses", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Orders> orders;

}
