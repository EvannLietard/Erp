package com.example.erp.dto;

/**
 * DTO pour la demande d'authentification.
 * <p>
 * Contient le nom d'utilisateur et le mot de passe nécessaires à la connexion.
 * </p>
 */
public class AuthRequest {
    private String username;
    private String password;

    /**
     * Constructeur sans argument requis pour la désérialisation.
     */
    public AuthRequest() {}

    /**
     * Constructeur avec tous les champs.
     * @param username le nom d'utilisateur
     * @param password le mot de passe
     */
    public AuthRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retourne le nom d'utilisateur.
     * @return username
     */
    public String getUsername() { return username; }

    /**
     * Définit le nom d'utilisateur.
     * @param username le nom d'utilisateur
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Retourne le mot de passe.
     * @return password
     */
    public String getPassword() { return password; }

    /**
     * Définit le mot de passe.
     * @param password le mot de passe
     */
    public void setPassword(String password) { this.password = password; }
}
