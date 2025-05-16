package com.example.mobilebackend.auth.controller;

import com.example.mobilebackend.auth.dto.AuthRequest;
import com.example.mobilebackend.auth.dto.AuthResponse;
import com.example.mobilebackend.auth.service.AuthService;
import com.example.mobilebackend.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        authService.register(user);
        return ResponseEntity.ok("Success регистрация на ура прошла!");
    }
}
