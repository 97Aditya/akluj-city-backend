package com.akluj.akluj_city_backend.repository;
import com.akluj.akluj_city_backend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {}
