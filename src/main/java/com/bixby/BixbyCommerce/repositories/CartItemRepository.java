package com.bixby.BixbyCommerce.repositories;

import com.bixby.BixbyCommerce.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItems, Long> {
}
