package com.example.erp_tmp.repository;   

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.erp_tmp.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

}
