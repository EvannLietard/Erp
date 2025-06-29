
package com.example.erp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Représente une société / entreprise utilisatrice.
 */
@Document(collection = "companies")
public class Company {

    @Id
    private String id;

    private String name;
    private String address;

    // Constructeurs
    public Company() {}

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters / Setters

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }
}
