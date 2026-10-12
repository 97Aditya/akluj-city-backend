package com.akluj.akluj_city_backend.services;

import com.akluj.akluj_city_backend.dto.AuthResponse;
import com.akluj.akluj_city_backend.dto.LoginRequest;
import com.akluj.akluj_city_backend.dto.RegisterRequest;
import com.akluj.akluj_city_backend.model.AppUser;
import com.akluj.akluj_city_backend.model.Role;
import com.akluj.akluj_city_backend.repository.UserRepository;
import com.akluj.akluj_city_backend.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(RegisterRequest request) throws Exception {
        try {
            if (userRepository.existsByUsername(request.getUsername())) {
                throw new RuntimeException("Username is already taken");
            }
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("Email is already in use");
            }

            AppUser user = new AppUser();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setRoles(Set.of(Role.ROLE_USER)); // default role

            userRepository.save(user);

            String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
            return new AuthResponse(token, user.getUsername(), "ROLE_USER");
        }catch (Exception e){
            throw new Exception("########### Error at register in user service #########"+ e.getMessage());
        }
    }

    public AuthResponse login(LoginRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            AppUser user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
            String role = user.getRoles().iterator().next().name();

            return new AuthResponse(token, user.getUsername(), role);
        }catch (Exception e){
            throw new Exception("########### Error at login in user service #########"+ e.getMessage());
        }
    }
}

