package com.akluj.akluj_city_backend.controller;
import com.akluj.akluj_city_backend.model.Restaurant;
import com.akluj.akluj_city_backend.model.Review;
import com.akluj.akluj_city_backend.repository.RestaurantRepository;
import com.akluj.akluj_city_backend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {
    @Autowired private ReviewRepository repo;
    @Autowired private RestaurantRepository restaurantRepo;

    @PostMapping
    public Review add(@RequestBody ReviewRequest req) throws Exception {
        try {
            Restaurant restaurant = restaurantRepo.findById(req.restaurantId)
                    .orElseThrow(() -> new Exception("Restaurant not found"));

            Review r = new Review();
            r.setAuthor(req.author);
            r.setRating(req.rating);
            r.setComment(req.comment);
            r.setRestaurant(restaurant);
            return repo.save(r);

        } catch (Exception e) {
            throw new Exception("Error at add Review: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Review> all() { return repo.findAll(); }
}

class ReviewRequest {
    public Long restaurantId;
    public String author;
    public Integer rating;
    public String comment;
}