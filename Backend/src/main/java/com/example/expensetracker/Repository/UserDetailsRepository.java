package com.example.expensetracker.Repository;

import com.example.expensetracker.Entity.User;

import com.example.expensetracker.Enum.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByProviderIdAndAuthProvider(String providerId, AuthProvider authProvider);
}
