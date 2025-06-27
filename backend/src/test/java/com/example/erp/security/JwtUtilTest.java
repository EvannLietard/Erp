package com.example.erp.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        userDetails = new User(
                "testuser",
                "password",
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    @Test
    void generateAndValidateToken() {
        String token = jwtUtil.generateToken(userDetails);
        assertNotNull(token);

        assertTrue(jwtUtil.validateToken(token, userDetails));
        assertEquals("testuser", jwtUtil.extractUsername(token));
    }

    @Test
    void tokenShouldContainRolesClaim() {
        String token = jwtUtil.generateToken(userDetails);
        String roles = jwtUtil.extractClaim(token, claims -> claims.get("roles").toString());
        assertTrue(roles.contains("ROLE_USER"));
    }

    @Test
    void expiredTokenShouldNotBeValid() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .toList());
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() - 1000); // Expiré il y a 1 seconde

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtUtil.getKey())
                .compact();

        assertFalse(jwtUtil.validateToken(token, userDetails));
    }
}
