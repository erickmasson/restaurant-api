package com.projeto.restaurant.resources;

import com.projeto.restaurant.entities.RestaurantTable;
import com.projeto.restaurant.services.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tables")
public class RestaurantTableResource {

    @Autowired
    private RestaurantTableService service;

    @GetMapping
    public ResponseEntity<List<RestaurantTable>> findAll(){
        List<RestaurantTable> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantTable> fingById(@PathVariable Long id){
        RestaurantTable obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<RestaurantTable> insert(@RequestBody RestaurantTable table){
        RestaurantTable savedTable = service.save(table);
        return ResponseEntity.ok(savedTable);
    }
}
