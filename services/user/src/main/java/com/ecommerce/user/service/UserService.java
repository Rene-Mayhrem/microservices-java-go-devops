package com.ecommerce.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.user.dto.AuthRequestDTO;
import com.ecommerce.user.dto.AuthResponseDTO;
import com.ecommerce.user.dto.UserRequestDTO;
import com.ecommerce.user.dto.UserResponseDTO;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwt;



    //? Register user
    public UserResponseDTO registerUser (UserRequestDTO request) {
        User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .role("USER")
            .build();
        User savedUser = repository.save(user);
        return UserResponseDTO.builder()
            .id(savedUser.getId())
            .username(savedUser.getUsername())
            .email(savedUser.getEmail())
            .firstName(savedUser.getFirstName())
            .lastName(savedUser.getLastName())
            .role(savedUser.getRole())
            .build();
    }

    public UserResponseDTO getUserByEmail (String email) {
        User user = repository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserResponseDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .role(user.getRole())
            .build();
    }
    


    //? Create new user
    public UserResponseDTO createUser (UserRequestDTO request) {
        User user = User.builder()
            .username(request.getUsername())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .role("USER")
            .build();
        User savedUser = repository.save(user);
        return mapToResponse(savedUser);
    }

    //? Get all users
    public List<UserResponseDTO> getAllUsers () {
        return repository.findAll()
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    //? Get user by id
    public UserResponseDTO getUserById (Long id) {
        User user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToResponse(user);
    }

    //? Update user
    public UserResponseDTO updateUser (UserRequestDTO request, Long id) {
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

    //? Mapper
    private UserResponseDTO mapToResponse(User user) {
        return UserResponseDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .build();
    }


}
