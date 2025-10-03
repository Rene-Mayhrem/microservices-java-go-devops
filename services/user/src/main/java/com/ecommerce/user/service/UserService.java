package com.ecommerce.user.service;

import org.springframework.stereotype.Service;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final repository;

    //? Create new user
    public UserResponse createUser (UserRequest request) {

    }

}
