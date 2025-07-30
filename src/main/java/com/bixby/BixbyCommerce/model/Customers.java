package com.bixby.BixbyCommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Generates a no-arg constructor
@AllArgsConstructor // Generates a constructor with all fields
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerID;

    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String password;

    @OneToMany(mappedBy = "customers",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Addresses> addresses;

    @OneToOne(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Orders> orders;

}
