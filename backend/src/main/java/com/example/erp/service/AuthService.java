package com.example.erp.service;

import com.example.erp.dto.AuthRequest;
import com.example.erp.dto.RegisterRequest;

/**
 * Service d'authentification pour la gestion des utilisateurs.
 * <p>
 * Définit les opérations d'inscription et de connexion.
 * </p>
 */
public interface AuthService {
    /**
     * Inscrit un nouvel utilisateur à partir des informations fournies.
     * @param request les informations d'inscription (username, password, email, etc.)
     * @return un message ou un token selon l'implémentation
     */
    String register(RegisterRequest request);

    /**
     * Authentifie un utilisateur à partir des informations fournies.
     * @param request les informations de connexion (username, password)
     * @return un token JWT si l'authentification réussit
     */
    String login(AuthRequest request);
}
