package com.projeto.restaurant.repositories;

import com.projeto.restaurant.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
