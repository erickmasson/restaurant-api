package com.projeto.restaurant.repositories;

import com.projeto.restaurant.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
