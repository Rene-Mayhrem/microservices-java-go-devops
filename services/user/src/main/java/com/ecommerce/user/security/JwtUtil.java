package com.ecommerce.user.security;

import java.security.Key;
import java.security.SignatureException;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Component;

import com.ecommerce.user.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtUtil { 
    private final SecretKey key; // TODO: put in config
    private final long expiration;

    public JwtUtil(
        @Value("${jwt.secret}") String secret, 
        @Value("${jwt.expiration}") long expiration) {
            this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
            this.expiration = expiration;
    }

    public String generateToken (User user) {
        return Jwts.builder()
            .setSubject(user.getEmail())
            .claim("id", user.getId())
            .claim("username", user.getUsername())
            .claim("role", user.getRole())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    //? Get email from token
    public String getEmailFromToken (String token) {
        return parseClaims(token).getSubject();
    }
    //? Get role from token
    public String getRoleFromToken (String token) {
        return (String) parseClaims(token).get("role");
    }
    //? Validate token
    public boolean validateToken (String token) {
        try {
            parseClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT expired: "+e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT: "+e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Malformed JWT: "+e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: "+e.getMessage());
        }
        return false;
    }

    private Claims parseClaims (String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
