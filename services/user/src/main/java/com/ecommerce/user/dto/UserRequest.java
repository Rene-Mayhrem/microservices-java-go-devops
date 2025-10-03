package com.ecommerce.user.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
