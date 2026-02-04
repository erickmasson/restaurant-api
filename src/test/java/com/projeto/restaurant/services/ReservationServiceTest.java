package com.projeto.restaurant.services;

import com.projeto.restaurant.dto.ReservationDTO;
import com.projeto.restaurant.entities.Reservation;
import com.projeto.restaurant.entities.RestaurantTable;
import com.projeto.restaurant.entities.User;
import com.projeto.restaurant.repositories.ReservationRepository;
import com.projeto.restaurant.repositories.RestaurantTableRepository;
import com.projeto.restaurant.repositories.UserRepository;
import com.projeto.restaurant.services.exceptions.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @InjectMocks
    private ReservationService service;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestaurantTableRepository tableRepository;

    @Test
    @DisplayName("Deve criar reserva com sucesso quanto tudo estiver OK")
    void shouldCreateReservationSuccess(){
        Long clientId = 1L;
        Long tableId = 1L;
        Instant moment = Instant.parse("2026-02-20T20:00:00Z");

        ReservationDTO dto = new ReservationDTO(clientId, tableId, moment, 2);

        User fakeUser = new User(); fakeUser.setId(clientId);
        RestaurantTable fakeTable = new RestaurantTable(); fakeTable.setId(tableId); fakeTable.setCapacity(4);

        when(userRepository.findById(clientId)).thenReturn(Optional.of(fakeUser));
        when(tableRepository.findById(tableId)).thenReturn(Optional.of(fakeTable));

        when(reservationRepository.existsConflict(any(), any(), any())).thenReturn(false);
        when(reservationRepository.save(any())).thenReturn(new Reservation());

        Reservation result = service.insert(dto);

        assertNotNull(result);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o número de pessoas for maior que a capacidade")
    void shouldThrowExceptionWhenCapacityExceeded(){
        Long tableId = 1L;
        ReservationDTO dto = new ReservationDTO(1L, tableId, Instant.now(), 6);

        RestaurantTable smallTable = new RestaurantTable();
        smallTable.setId(tableId);
        smallTable.setCapacity(2);

        User fakeUser = new User(); fakeUser.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(fakeUser));
        when(tableRepository.findById(tableId)).thenReturn(Optional.of(smallTable));

        assertThrows(BusinessException.class, () -> {
            service.insert(dto);
        });
    }

    @Test
    @DisplayName("Deve lançar exceção quando houver conflito de horário (Overbooking)")
    void shouldThrowExceptionWhenTimeConflict(){
        Long tableId = 1l;
        ReservationDTO dto = new ReservationDTO(1L, tableId, Instant.parse("2026-02-20T20:00:00Z"), 2);

        User fakeUser = new User(); fakeUser.setId(1L);
        RestaurantTable fakeTable = new RestaurantTable(); fakeTable.setId(tableId); fakeTable.setCapacity(4);

        when(userRepository.findById(1L)).thenReturn(Optional.of(fakeUser));
        when(tableRepository.findById(tableId)).thenReturn(Optional.of(fakeTable));

        when(reservationRepository.existsConflict(any(), any(), any())).thenReturn(true);

        assertThrows(BusinessException.class, () -> {
            service.insert(dto);
        });
    }
}