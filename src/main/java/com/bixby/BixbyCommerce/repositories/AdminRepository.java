package com.bixby.BixbyCommerce.repositories;

import com.bixby.BixbyCommerce.model.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Integer> {
}
