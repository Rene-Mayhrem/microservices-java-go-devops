package com.ecommerce.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.dto.AuthRequestDTO;
import com.ecommerce.user.dto.AuthResponseDTO;
import com.ecommerce.user.dto.UserRequestDTO;
import com.ecommerce.user.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;

    public AuthController (AuthService service) {
        this.service = service;
    }

    //? POST /auth/register
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register (@Valid @RequestBody UserRequestDTO request) {
        AuthResponseDTO response = service.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //? POST /auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@Valid @RequestBody AuthRequestDTO request) {
        AuthResponseDTO response = service.login(request);
        return ResponseEntity.ok(response);
    }
}
