package com.example.usermanagementapp.repository;

import com.example.usermanagementapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);  // Check if username exists

    boolean existsByEmail(String email);  // Check if email exists
}
