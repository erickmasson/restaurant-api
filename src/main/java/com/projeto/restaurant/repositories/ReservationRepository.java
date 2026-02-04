package com.projeto.restaurant.repositories;

import com.projeto.restaurant.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT COUNT(r) > 0 FROM Reservation r "
            + "WHERE r.table.id = :tableId "
            + "AND r.moment >= :start "
            + "AND r.moment <= :end")
    boolean existsConflict(Long tableId, Instant start, Instant end);
}
