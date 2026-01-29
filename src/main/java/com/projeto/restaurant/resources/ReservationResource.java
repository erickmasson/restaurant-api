package com.projeto.restaurant.resources;

import com.projeto.restaurant.dto.ReservationDTO;
import com.projeto.restaurant.entities.Reservation;
import com.projeto.restaurant.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<Reservation> insert(@RequestBody ReservationDTO dto){
        Reservation obj = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).body(obj);
    }
}
