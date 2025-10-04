package com.ecommerce.user.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private static final String secret = "6B5970337336763979244226452948404D635166546A576E5A72347537782141";

}
