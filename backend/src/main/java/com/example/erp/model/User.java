package com.example.erp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

/**
 * Modèle représentant un utilisateur de l'application.
 * <p>
 * Un utilisateur possède un identifiant, un nom d'utilisateur, un mot de passe (stocké hashé)
 * et un ensemble d'identifiants de rôles pour la gestion des autorisations.
 * </p>
 */
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String password; // stocké hashé
    private Set<String> roleIds; // IDs des rôles

    /**
     * Constructeur sans argument requis pour la désérialisation.
     */
    public User() {}

    /**
     * Constructeur avec tous les champs sauf l'id.
     * @param username le nom d'utilisateur
     * @param password le mot de passe hashé
     * @param roleIds les identifiants des rôles associés à l'utilisateur
     */
    public User(String username, String password, Set<String> roleIds) {
        this.username = username;
        this.password = password;
        this.roleIds = roleIds;
    }

    /**
     * Retourne l'identifiant de l'utilisateur.
     * @return id
     */
    public String getId() { return id; }

    /**
     * Définit l'identifiant de l'utilisateur.
     * @param id l'identifiant
     */
    public void setId(String id) { this.id = id; }

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
     * Retourne le mot de passe hashé.
     * @return password
     */
    public String getPassword() { return password; }

    /**
     * Définit le mot de passe hashé.
     * @param password le mot de passe hashé
     */
    public void setPassword(String password) { this.password = password; }

    /**
     * Retourne les identifiants des rôles associés à l'utilisateur.
     * @return roleIds
     */
    public Set<String> getRoleIds() { return roleIds; }

    /**
     * Définit les identifiants des rôles associés à l'utilisateur.
     * @param roleIds les identifiants des rôles
     */
    public void setRoleIds(Set<String> roleIds) { this.roleIds = roleIds; }
}
