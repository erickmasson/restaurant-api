package com.projeto.restaurant.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.restaurant.dto.ReservationDTO;
import com.projeto.restaurant.entities.Reservation;
import com.projeto.restaurant.entities.enums.ReservationStatus;
import com.projeto.restaurant.entities.RestaurantTable;
import com.projeto.restaurant.entities.User;
import com.projeto.restaurant.repositories.ReservationRepository;
import com.projeto.restaurant.repositories.RestaurantTableRepository;
import com.projeto.restaurant.repositories.UserRepository;
import com.projeto.restaurant.services.exceptions.BusinessException;
import com.projeto.restaurant.services.exceptions.ResourceNotFoundException;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantTableRepository tableRepository;

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public Reservation findById(Long id) {
        Optional<Reservation> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Reservation insert(ReservationDTO dto) {
        User client = userRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado. Id: " + dto.getClientId()));

        RestaurantTable table = tableRepository.findById(dto.getTableId())
                .orElseThrow(() -> new ResourceNotFoundException("Mesa não encontrada. Id: " + dto.getTableId()));

        if (dto.getPartySize() > table.getCapacity()) {
            throw new BusinessException("A mesa " + table.getNumber() + " (capacidade: " + table.getCapacity()
                    + ") não comporta " + dto.getPartySize() + " pessoas.");
        }

        Instant novaReserva = dto.getMoment();
        Instant inicioJanela = novaReserva.minus(2, ChronoUnit.HOURS).plusSeconds(1);
        Instant fimJanela = novaReserva.plus(2, ChronoUnit.HOURS).minusSeconds(1);

        boolean conflito = repository.existsConflict(dto.getTableId(), inicioJanela, fimJanela);
        if(conflito){
            throw new BusinessException("A mesa " + table.getNumber()
                + " já está reservada para este horário (Intervalo de conflito: +/- 2 horas)."
            );
        }

        Reservation entity = new Reservation();
        entity.setMoment(dto.getMoment());
        entity.setPartySize(dto.getPartySize());
        entity.setStatus(ReservationStatus.PENDING);
        entity.setClient(client);
        entity.setTable(table);

        return repository.save(entity);
    }
}