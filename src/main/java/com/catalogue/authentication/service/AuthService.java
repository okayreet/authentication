package com.catalogue.authentication.service;

import org.springframework.stereotype.Service;

import com.catalogue.authentication.config.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    public String generateToken(String username) {
        return jwtUtil.generateToken(username);
    }

    // public void validateToken(String token) {
    //     jwtUtil.validateToken(token);
    // }
}
