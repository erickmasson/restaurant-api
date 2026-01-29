package com.projeto.restaurant.repositories;

import com.projeto.restaurant.entities.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Long> {
}
