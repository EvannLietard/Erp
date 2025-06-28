package com.example.erp.dto;

/**
 * DTO pour la demande d'inscription d'un nouvel utilisateur.
 * <p>
 * Contient le nom d'utilisateur, le mot de passe et l'email nécessaires à la création d'un compte.
 * </p>
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    /**
     * Constructeur sans argument requis pour la désérialisation.
     */
    public RegisterRequest() {}

    /**
     * Constructeur avec tous les champs.
     * @param username le nom d'utilisateur
     * @param password le mot de passe
     * @param email l'adresse email
     */
    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    /**
     * Retourne l'adresse email.
     * @return email
     */
    public String getEmail() { return email; }

    /**
     * Définit l'adresse email.
     * @param email l'adresse email
     */
    public void setEmail(String email) { this.email = email; }
}
