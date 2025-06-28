package com.example.erp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Modèle représentant un rôle utilisateur dans l'application.
 * <p>
 * Un rôle définit les autorisations d'accès d'un utilisateur (ex : ROLE_USER, ROLE_ADMIN).
 * </p>
 */
@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private String name;

    /**
     * Constructeur sans argument requis pour la désérialisation.
     */
    public Role() {
    }

    /**
     * Constructeur avec le nom du rôle.
     * @param name le nom du rôle (ex : ROLE_USER)
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Retourne l'identifiant du rôle.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant du rôle.
     * @param id l'identifiant
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retourne le nom du rôle.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom du rôle.
     * @param name le nom du rôle
     */
    public void setName(String name) {
        this.name = name;
    }
}
