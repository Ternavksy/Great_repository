package com.example.mobilebackend.auth.service;

import com.example.mobilebackend.auth.dto.AuthRequest;
import com.example.mobilebackend.auth.dto.AuthResponse;
import com.example.mobilebackend.auth.exception.AuthException;
import com.example.mobilebackend.auth.security.JwtTokenProvider;
import com.example.mobilebackend.user.model.User;
import com.example.mobilebackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new AuthException("Пользователь не найден!"));
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new AuthException("Неправильный пароль!");
        }
        String token = jwtTokenProvider.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(token);
    }

    public void register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new AuthException("Имя уже занято!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
    }
}

