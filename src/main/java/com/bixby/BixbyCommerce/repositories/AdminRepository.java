package com.bixby.BixbyCommerce.repositories;

import com.bixby.BixbyCommerce.model.Admins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admins, Integer> {
    Optional<Admins> findByEmail(String email);
}
