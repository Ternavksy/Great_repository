package com.example.mobilebackend;

import com.example.mobilebackend.auth.controller.AuthController;
import com.example.mobilebackend.auth.dto.AuthRequest;
import com.example.mobilebackend.auth.dto.AuthResponse;
import com.example.mobilebackend.auth.service.AuthService;
import com.example.mobilebackend.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLogin_Success() {
        AuthRequest authRequest = new AuthRequest("testuser", "password"); // Теперь этот код работает
        AuthResponse authResponse = new AuthResponse("jwt-token");
        when(authService.login(any(AuthRequest.class))).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = authController.login(authRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(authResponse, response.getBody());
        verify(authService, times(1)).login(authRequest);
    }

    @Test
    void testRegister_Success() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        doNothing().when(authService).register(any(User.class));

        ResponseEntity<String> response = authController.register(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Success регистрация на ура прошла!", response.getBody());
        verify(authService, times(1)).register(user);
    }
}