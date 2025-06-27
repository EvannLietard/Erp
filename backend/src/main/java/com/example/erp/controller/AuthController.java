package com.example.erp.controller;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.example.erp.dto.AuthRequest;
import com.example.erp.dto.AuthResponse;
import com.example.erp.dto.RegisterRequest;
import com.example.erp.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.time.Duration;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request,
            HttpServletResponse response,
            HttpServletRequest httpRequest) {

        String token = authService.login(request); // Renommez login() en authenticate()

        // Configuration du cookie
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(isSecureConnection(httpRequest)) // Seulement en HTTPS
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("Strict")
                .domain(getDomain(httpRequest)) // Important en prod
                .build();

        
        // Dans le corps de réponse
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new AuthResponse(token));
    }

    private boolean isSecureConnection(HttpServletRequest request) {
        return request.isSecure() || Arrays.asList("localhost", "127.0.0.1").contains(request.getServerName());
    }

    private String getDomain(HttpServletRequest request) {
        String serverName = request.getServerName();
        if (serverName.contains("localhost") || serverName.contains("127.0.0.1")) {
            return null;
        }
        return serverName.substring(serverName.indexOf('.'));
    }
}
