package com.ecommerce.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.dto.UserRequestDTO;
import com.ecommerce.user.dto.UserResponseDTO;
import com.ecommerce.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUser (@PathVariable String email) {
        return ResponseEntity.ok(service.getUserByEmail(email));
    }

    //? CREATE NEW USER
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser (@Valid @RequestBody UserRequestDTO request){
        return ResponseEntity.ok(service.createUser(request));
    }

    //? GET ALL USERS

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers () {
        return ResponseEntity.ok(service.getAllUsers());
    }

    //? GET USER BY ID
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById (@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    //? DELETE USER
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //? UPDATE USER
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser (@Valid @PathVariable Long id,
                                                    @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(service.updateUser(request, id));
    }
}
