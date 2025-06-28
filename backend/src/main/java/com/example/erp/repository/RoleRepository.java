package com.example.erp.repository;   

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.erp.model.Role;
import java.util.Optional;

/**
 * Repository Spring Data pour l'accès aux rôles en base MongoDB.
 * <p>
 * Permet de récupérer un rôle par son nom.
 * </p>
 */
public interface RoleRepository extends MongoRepository<Role, String> {
    /**
     * Recherche un rôle par son nom.
     * @param name le nom du rôle (ex : ROLE_USER)
     * @return un Optional contenant le rôle s'il existe, vide sinon
     */
    Optional<Role> findByName(String name);
}
