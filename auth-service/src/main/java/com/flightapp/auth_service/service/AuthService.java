package com.flightapp.auth_service.service;

import com.flightapp.auth_service.dto.LoginRequest;
import com.flightapp.auth_service.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    String login(LoginRequest request);
}
