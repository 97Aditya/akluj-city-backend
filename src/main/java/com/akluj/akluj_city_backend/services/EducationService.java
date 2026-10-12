package com.akluj.akluj_city_backend.services;
import com.akluj.akluj_city_backend.model.Education;
import com.akluj.akluj_city_backend.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class EducationService {

    @Autowired
    private EducationRepository repository;

    public List<Education> getAll() {
        return repository.findAll();
    }

    public Education getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Education create(Education education) {
        return repository.save(education);
    }

    public Education update(Long id, Education education) {
        Optional<Education> existing = repository.findById(id);
        if (existing.isPresent()) {
            Education edu = existing.get();
            edu.setName(education.getName());
            edu.setType(education.getType());
            edu.setDescription(education.getDescription());
            edu.setWebsite(education.getWebsite());
            // edu.setPhoto(education.getPhoto());
            return repository.save(edu);
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
