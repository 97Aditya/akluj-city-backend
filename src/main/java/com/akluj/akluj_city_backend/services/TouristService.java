package com.akluj.akluj_city_backend.services;

import com.akluj.akluj_city_backend.model.Tourist;
import com.akluj.akluj_city_backend.repository.TouristRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TouristService {
    @Autowired
    private TouristRepository repository;

    public List<Tourist> getAll() {
        return repository.findAll();
    }

    public Tourist getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Tourist create(Tourist tourist) {
        System.out.println(tourist);
        return repository.save(tourist);
    }

    public Tourist update(Long id, Tourist tourist) {
        Optional<Tourist> existing = repository.findById(id);
        if (existing.isPresent()) {
            Tourist tourist1 = existing.get();
            tourist1.setName(tourist.getName());
            tourist1.setDescription(tourist.getDescription());
            tourist1.setLocation(tourist.getLocation());
            tourist1.setPhotos(tourist.getPhotos());
            tourist1.setVideos(tourist.getVideos());
            return repository.save(tourist1);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
