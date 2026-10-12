package com.akluj.akluj_city_backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String type; // veg, nonveg, both

    @Column(length = 2000)
    private String description;

    @ElementCollection
    @CollectionTable(name = "restaurant_images", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "image_url", length = 1000)
    private List<String> images = new ArrayList<>();

    // ✅ Menu: still fine as unidirectional
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "restaurant_id")
    private List<MenuItem> menu = new ArrayList<>();

    // ✅ Fixed reviews mapping (bidirectional)
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

    // --------------------------
    // Getters & Setters
    // --------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public List<MenuItem> getMenu() { return menu; }
    public void setMenu(List<MenuItem> menu) { this.menu = menu; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) {
        this.reviews.clear();
        if (reviews != null) {
            for (Review r : reviews) {
                r.setRestaurant(this); // maintain bidirectional link
                this.reviews.add(r);
            }
        }
    }
}
