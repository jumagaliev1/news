package com.github.jumagaliev1.backendAssignment.service;

import com.github.jumagaliev1.backendAssignment.model.request.SignInRequest;
import com.github.jumagaliev1.backendAssignment.model.request.SignUpRequest;
import com.github.jumagaliev1.backendAssignment.model.response.JwtResponse;

public interface AuthService {
    void registerUser(SignUpRequest request);

    JwtResponse authenticate(SignInRequest request);
}
