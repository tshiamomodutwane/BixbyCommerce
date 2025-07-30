package com.bixby.BixbyCommerce.repositories;

import com.bixby.BixbyCommerce.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
}
