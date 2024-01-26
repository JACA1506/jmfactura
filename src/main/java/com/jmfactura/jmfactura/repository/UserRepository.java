package com.jmfactura.jmfactura.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmfactura.jmfactura.models.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
