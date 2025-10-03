package com.ecommerce.user.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    //? CREATE NEW USER
    @PostMapping
    public ResponseEntity<UserResponse> createUser (@Valid @RequestBody UserRequest request){
        return ResponseEntity.ok(service.createUser(request));
    }

    //? GET ALL USERS
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers () {
        return ResponseEntity.ok(service.getAllUsers());
    }

    //? GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById (@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    //? DELETE USER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        service.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    //? UPDATE USER
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser (@Valid @PathVariable Long id,
                                                    @RequestBody UserRequest request) {
        return ResponseEntity.ok(service.updateUser(request, id));
    }
}
