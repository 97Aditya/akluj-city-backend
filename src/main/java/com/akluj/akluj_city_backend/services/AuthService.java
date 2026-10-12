package com.akluj.akluj_city_backend.services;

import com.akluj.akluj_city_backend.model.AppUser;
import com.akluj.akluj_city_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser validateUser(String username, String rawPassword) {
        return userRepo.findByUsername(username)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .orElse(null);
    }
}
