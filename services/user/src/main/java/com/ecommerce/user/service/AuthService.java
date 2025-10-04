package com.ecommerce.user.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user.dto.AuthRequestDTO;
import com.ecommerce.user.dto.AuthResponseDTO;
import com.ecommerce.user.dto.UserRequestDTO;
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
    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO login (AuthRequestDTO request) {
        User user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UserNotFoundException("Invalid Credentials"));
        
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }
        String token = jwt.generateToken(user);
        return AuthResponseDTO.builder()
            .token(token)
            .email(user.getEmail())
            .username(user.getEmail())
            .build();
    }

    public AuthResponseDTO register (UserRequestDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = repository.save(user);

        String token = jwt.generateToken(user);
        return AuthResponseDTO.builder()
            .token(token)
            .email(saved.getEmail())
            .username(saved.getUsername())
            .build();
    }
}
