package com.akluj.akluj_city_backend.controller;

import com.akluj.akluj_city_backend.model.AppUser;
import com.akluj.akluj_city_backend.model.Role;
import com.akluj.akluj_city_backend.repository.UserRepository;
import com.akluj.akluj_city_backend.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) throws Exception {
        try {
            if (userRepository.existsByUsername(user.getUsername())) {
                return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
            }
            if (userRepository.existsByUsername(user.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of("error", "User email already exists"));
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (user.getRoles() == null || user.getRoles().isEmpty()) {
                user.setRoles(Set.of(Role.ROLE_USER)); // default role
            }
            userRepository.save(user);

            return ResponseEntity.ok(Map.of("message", "User registered successfully"));
        }catch (Exception e){
            throw new Exception("########### Error at register in auth controller#########"+ e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) throws Exception {
        String username = request.get("username");
        String password = request.get("password");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            AppUser user = userRepository.findByUsername(username).orElseThrow();
            String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "username", user.getUsername(),
                    "roles", user.getRoles()
            ));
        }catch (Exception e){
            throw new Exception("########### Error at Login in auth controller#########"+ e.getMessage());
        }
    }
}
