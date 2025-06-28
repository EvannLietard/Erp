package com.example.erp.dto;

/**
 * DTO pour la réponse d'authentification.
 * <p>
 * Contient le token JWT retourné après une connexion réussie.
 * </p>
 */
public class AuthResponse {
    private String token;

    /**
     * Constructeur sans argument requis pour la désérialisation.
     */
    public AuthResponse() {}

    /**
     * Constructeur avec le token.
     * @param token le token JWT
     */
    public AuthResponse(String token) {
        this.token = token;
    }

    /**
     * Retourne le token JWT.
     * @return token
     */
    public String getToken() { return token; }

    /**
     * Définit le token JWT.
     * @param token le token JWT
     */
    public void setToken(String token) { this.token = token; }
}
