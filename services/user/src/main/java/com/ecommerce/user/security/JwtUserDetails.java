package com.ecommerce.user.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtUserDetails {
    private Long id;
    private String email;
    private String role;
}
