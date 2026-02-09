package com.movierental.store.repository;

import com.movierental.store.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This helper method helps us find a user by their name during login
    Optional<User> findByUsername(String username);
}