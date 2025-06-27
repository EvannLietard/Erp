package com.example.erp_tmp.dto;

public class AuthResponse {
    private String token;

    public AuthResponse() {}

    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter et setter
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
