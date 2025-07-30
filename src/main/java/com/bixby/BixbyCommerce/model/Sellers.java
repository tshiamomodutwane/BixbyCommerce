package com.bixby.BixbyCommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sellers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sellers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private Long sellerID;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "company_registration")
    private String companyRegistration;

    @Column(name = "product_categories")
    private String productCategories;

    @Column(name = "banking_info")
    private String bankingInfo;

    private String address;

    @Column(name = "profile_image")
    private String profileImage;


    @Column(name = "vat_number", unique = true, nullable = false)
    private String vatNumber;

    @OneToMany(mappedBy = "sellers", cascade = CascadeType.ALL)
    private List<Products> products;
}
