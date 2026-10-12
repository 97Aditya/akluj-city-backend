package com.akluj.akluj_city_backend.controller;

import com.akluj.akluj_city_backend.model.Tourist;
import com.akluj.akluj_city_backend.services.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tourists")
@CrossOrigin(origins = "http://localhost:4200")
public class TouristController {

    @Autowired
    private TouristService service;

    @GetMapping
    public List<Tourist> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Tourist getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Tourist create(@RequestBody Tourist tourist) {
        System.out.println(tourist);
        return service.create(tourist);
    }

    @PutMapping("/{id}")
    public Tourist update(@PathVariable Long id, @RequestBody Tourist tourist) {
        return service.update(id, tourist);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("message", "Tourist Place deleted successfully");
        } else {
            response.put("message", "Tourist Place not found");
        }
        return response;
    }


}
