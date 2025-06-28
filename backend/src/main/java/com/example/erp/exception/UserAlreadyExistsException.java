package com.example.erp.exception;

/**
 * Exception levée lorsqu'un utilisateur existe déjà lors d'une tentative d'inscription.
 */
public class UserAlreadyExistsException extends RuntimeException {
    /**
     * Construit une nouvelle exception avec le message spécifié.
     * @param message le message d'erreur
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}