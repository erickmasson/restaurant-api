package com.projeto.restaurant.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projeto.restaurant.dto.ReservationDTO;
import com.projeto.restaurant.entities.Reservation;
import com.projeto.restaurant.services.ReservationService;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationResource {

    @Autowired
    private ReservationService service;

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll() {
        List<Reservation> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        Reservation obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Reservation> insert(@RequestBody ReservationDTO dto) {
        Reservation obj = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }
}