package com.akluj.akluj_city_backend.repository;
import com.akluj.akluj_city_backend.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByTypeIgnoreCase(String type);

    @Query("SELECT r FROM Restaurant r LEFT JOIN FETCH r.reviews")
    List<Restaurant> findAllWithReviews();
}