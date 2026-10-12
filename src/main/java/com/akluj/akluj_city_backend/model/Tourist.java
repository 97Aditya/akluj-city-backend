package com.akluj.akluj_city_backend.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Lob @Column(columnDefinition = "TEXT")
    private String description;
    private String location;

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }

    @ElementCollection
    @CollectionTable(name = "tourist_photos", joinColumns = @JoinColumn(name = "tourist_id"))
    @Column(name = "photo_url", length = 500)
    private List<String> photos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "tourist_videos", joinColumns = @JoinColumn(name = "tourist_id"))
    @Column(name = "video_url", length = 500)
    private List<String> videos = new ArrayList<>();

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
