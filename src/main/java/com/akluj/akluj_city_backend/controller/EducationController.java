package com.akluj.akluj_city_backend.controller;
import com.akluj.akluj_city_backend.model.Education;
import com.akluj.akluj_city_backend.services.EducationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/education")
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {

    @Autowired
    private EducationService service;

    @GetMapping
    public List<Education> getAll() throws Exception {
        try{
            return service.getAll();
        }catch (Exception e){
            throw new Exception("#########Error At Get educations#########"+e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Education getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Education create(@RequestBody Education education) {
        return service.create(education);
    }

    @PutMapping("/{id}")
    public Education update(@PathVariable Long id, @RequestBody Education education) {
        return service.update(id, education);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        Map<String, String> response = new HashMap<>();
        if (deleted) {
            response.put("message", "Education deleted successfully");
        } else {
            response.put("message", "Education not found");
        }
        return response;
    }
}
