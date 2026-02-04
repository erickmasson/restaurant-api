package com.projeto.restaurant.services;

import com.projeto.restaurant.entities.RestaurantTable;
import com.projeto.restaurant.repositories.RestaurantTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantTableService {
    @Autowired
    private RestaurantTableRepository repository;

    public List<RestaurantTable> findAll(){
        return repository.findAll();
    }

    public RestaurantTable findById(Long id){
        Optional<RestaurantTable> obj = repository.findById(id);
        return obj.orElse(null);
    }

    public RestaurantTable save(RestaurantTable table){
        return repository.save(table);
    }
}
