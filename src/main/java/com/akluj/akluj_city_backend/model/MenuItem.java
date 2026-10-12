package com.akluj.akluj_city_backend.model;

import jakarta.persistence.*;

@Entity
public class MenuItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double price;
    @Column(length=1000)
    private String imageUrl;

    // getters/setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public String getCategory(){ return category; }
    public void setCategory(String category){ this.category = category; }
    public Double getPrice(){ return price; }
    public void setPrice(Double price){ this.price = price; }
    public String getImageUrl(){ return imageUrl; }
    public void setImageUrl(String imageUrl){ this.imageUrl = imageUrl; }
}
