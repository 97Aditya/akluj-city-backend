package com.akluj.akluj_city_backend.controller;
import com.akluj.akluj_city_backend.model.Reservation;
import com.akluj.akluj_city_backend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {
    @Autowired private ReservationRepository repo;

    @PostMapping
    public Reservation book(@RequestBody Reservation r) {
        return repo.save(r);
    }
}