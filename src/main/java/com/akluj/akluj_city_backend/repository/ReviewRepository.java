package com.akluj.akluj_city_backend.repository;

import com.akluj.akluj_city_backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {}
