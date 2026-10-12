package com.akluj.akluj_city_backend.controller;

import com.akluj.akluj_city_backend.model.Restaurant;
import com.akluj.akluj_city_backend.model.Review;
import com.akluj.akluj_city_backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:4200")
public class RestaurantController {

    @Autowired
    private RestaurantRepository repo;

    // ✅ Get all restaurants (filter by type optional)
    @GetMapping
    public List<Restaurant> list(@RequestParam(name = "type", required = false) String type) throws Exception {
        try {
            if (type == null || type.isBlank() || type.isEmpty()) return this.getAll();
            return repo.findByTypeIgnoreCase(type);
        } catch (Exception e) {
            throw new Exception("####### Error at get all restaurants: " + e.getMessage());
        }
    }


    // ✅ Get restaurant by ID
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create new restaurant
    @PostMapping
    public Restaurant create(@RequestBody Restaurant r) {
        return repo.save(r);
    }

    // ✅ Update restaurant — without touching reviews (fix orphan issue)
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody Restaurant payload) throws Exception {
        try {
            return repo.findById(id).map(existing -> {
                existing.setName(payload.getName());
                existing.setLocation(payload.getLocation());
                existing.setType(payload.getType());
                existing.setDescription(payload.getDescription());
                existing.setImages(payload.getImages() == null ? List.of() : payload.getImages());

                // safely update menu
                existing.getMenu().clear();
                if (payload.getMenu() != null) {
                    existing.getMenu().addAll(payload.getMenu());
                }

                // ❌ Do NOT modify reviews here (handled separately)

                Restaurant saved = repo.save(existing);
                return ResponseEntity.ok(saved);
            }).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            throw new Exception("Error at Update Restaurant: " + e.getMessage(), e);
        }
    }

    // ✅ Delete restaurant
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        repo.deleteById(id);
        return ResponseEntity.ok(Map.of("status", "deleted"));
    }

    // ✅ Add new review to a restaurant (separate endpoint)
    @PostMapping("/{id}/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Long id, @RequestBody Review review) {
        return repo.findById(id).map(restaurant -> {
            review.setId(null); // ensure new review
            restaurant.getReviews().add(review);
            repo.save(restaurant);
            return ResponseEntity.ok(review);
        }).orElse(ResponseEntity.notFound().build());
    }

    public List<Restaurant> getAll() {
        return repo.findAllWithReviews();
    }
}
