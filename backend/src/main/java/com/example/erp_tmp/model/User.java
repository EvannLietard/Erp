package com.example.erp_tmp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String password; // stocké hashé
    private Set<String> roleIds; // IDs des rôles

    public User() {}

    public User(String username, String password, Set<String> roleIds) {
        this.username = username;
        this.password = password;
        this.roleIds = roleIds;
    }

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Set<String> getRoleIds() { return roleIds; }
    public void setRoleIds(Set<String> roleIds) { this.roleIds = roleIds; }
}
