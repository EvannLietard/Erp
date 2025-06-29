package com.example.erp.repository;   

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.erp.model.User;
import java.util.Optional;

/**
 * Repository Spring Data pour l'accès aux utilisateurs en base MongoDB.
 * <p>
 * Permet de récupérer un utilisateur par son nom d'utilisateur.
 * </p>
 */
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     * @param username le nom d'utilisateur
     * @return un Optional contenant l'utilisateur s'il existe, vide sinon
     */
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndCompanyIdIsNull(String username);

}
