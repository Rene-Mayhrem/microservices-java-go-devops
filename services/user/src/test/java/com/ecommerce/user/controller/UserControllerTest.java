package com.ecommerce.user.controller;

import static org.mockito.Mockito.when;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.ecommerce.user.dto.UserResponseDTO;
import com.ecommerce.user.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private UserService service;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void shouldReturnUserById_whenAdminAcesses() throws Exception {
        UserResponseDTO mockUser = new UserResponseDTO(
            1L,
            "testuser",
            "testuser@gmail.com",
            "user",
            "username",
            "USER"
        );
        when(service.getUserById(1L)).thenReturn(mockUser);

        mock.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("testUser"))
            .andExpect(jsonPath("$.email").value("testuser@gmail.com"));
    }

    @Test
    void shouldReturnUnauthorized_whenNoAuthHeader() throws Exception {
        mock.perform(get("users/1"))
            .andExpect(status().isForbidden());
    }
}
