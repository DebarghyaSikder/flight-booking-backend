package com.flightapp.auth_service.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.flightapp.auth_service.dto.LoginRequest;
import com.flightapp.auth_service.dto.RegisterRequest;
import com.flightapp.auth_service.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @RequestBody RegisterRequest request) {

        String result = authService.register(request);

        if ("User already exists with this email".equals(result)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    result
            );
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("message", result));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody LoginRequest request) {

        String token = authService.login(request);

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }
}
