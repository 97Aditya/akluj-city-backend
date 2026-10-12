package com.akluj.akluj_city_backend.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {

    @GetMapping("/user")
    public String userAccess() {
        return "Welcome USER 👤 — you have limited access!";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Welcome ADMIN 👑 — you have full access!";
    }

    @GetMapping("/public")
    public String publicAccess() {
        return "This is a public endpoint.";
    }
}
