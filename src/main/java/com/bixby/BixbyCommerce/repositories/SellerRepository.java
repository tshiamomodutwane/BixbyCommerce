package com.bixby.BixbyCommerce.repositories;

import com.bixby.BixbyCommerce.model.Sellers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Sellers, Long> {
}
