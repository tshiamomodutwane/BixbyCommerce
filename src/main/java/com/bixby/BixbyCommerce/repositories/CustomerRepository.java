package com.bixby.BixbyCommerce.repositories;

import com.bixby.BixbyCommerce.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {
}
