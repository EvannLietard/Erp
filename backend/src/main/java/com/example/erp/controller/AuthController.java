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



/**
 * Contrôleur REST pour la gestion de l'authentification des utilisateurs.
 * <p>
 * Fournit les endpoints pour l'inscription et la connexion.
 * Les tokens JWT sont retournés dans un cookie sécurisé.
 * </p>
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Injection du service d'authentification.
     * @param authService service métier pour l'authentification
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint d'inscription d'un nouvel utilisateur.
     * @param request les informations d'inscription (username, password, etc.)
     * @return 200 OK si l'utilisateur est créé
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    /**
     * Endpoint de connexion utilisateur.
     * <p>
     * Retourne un JWT dans un cookie sécurisé et dans le corps de la réponse.
     * </p>
     * @param request les informations de connexion (username, password)
     * @param response la réponse HTTP
     * @param httpRequest la requête HTTP
     * @return AuthResponse contenant le JWT
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request,
            HttpServletResponse response,
            HttpServletRequest httpRequest) {

        String token = authService.login(request);

        // Configuration du cookie JWT
        ResponseCookie jwtCookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(isSecureConnection(httpRequest))
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("Strict")
                .domain(getDomain(httpRequest))
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new AuthResponse(token));
    }

    /**
     * Détermine si la connexion est sécurisée (HTTPS ou localhost).
     * @param request la requête HTTP
     * @return true si la connexion est sécurisée
     */
    private boolean isSecureConnection(HttpServletRequest request) {
        return request.isSecure() || Arrays.asList("localhost", "127.0.0.1").contains(request.getServerName());
    }

    /**
     * Récupère le domaine pour le cookie JWT.
     * @param request la requête HTTP
     * @return le domaine, ou null pour localhost
     */
    private String getDomain(HttpServletRequest request) {
        String serverName = request.getServerName();
        if (serverName.contains("localhost") || serverName.contains("127.0.0.1")) {
            return null;
        }
        return serverName.substring(serverName.indexOf('.'));
    }
}
