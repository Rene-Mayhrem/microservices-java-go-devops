package com.ecommerce.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user.dto.AuthRequest;
import com.ecommerce.user.dto.AuthResponse;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository repository;
    private final JwtUtil jwt;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login (AuthRequest request) {
        User user = repository.findByUsername(request.getUsername())
            .orElseThrow(() -> new UserNotFoundException("Invalid Credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
        String token = jwt.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
