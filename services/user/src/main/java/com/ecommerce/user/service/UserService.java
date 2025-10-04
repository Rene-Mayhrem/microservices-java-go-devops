package com.ecommerce.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    //? Create new user
    public UserResponse createUser (UserRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .build();
        User savedUser = repository.save(user);
        return mapToResponse(savedUser);
    }

    //? Get all users
    public List<UserResponse> getAllUsers () {
        return repository.findAll()
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    //? Get user by id
    public UserResponse getUserById (Long id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToResponse(user);
    }

    //? Update user
    public UserResponse updateUser (UserRequest request, Long id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User udpatedUser = repository.save(user); //? update user
        return mapToResponse(udpatedUser);
    }

    //? Delete user
    public void deleteUser (Long id) {
        repository.deleteById(id);
    }

    //? Mapper
    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();
    }


}
