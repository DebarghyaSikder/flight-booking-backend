package com.flightapp.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthTestController {

    @GetMapping("/auth/health")
    public String health() {
        return "Auth Service is UP";
    }
}