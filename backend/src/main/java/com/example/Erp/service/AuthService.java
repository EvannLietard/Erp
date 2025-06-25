package com.example.erp.service;

import com.example.erp.dto.AuthRequest;
import com.example.erp.dto.RegisterRequest;
import com.example.erp.security.CustomUserDetailsService;

public interface AuthService {
    String register(RegisterRequest request);
    String login(AuthRequest request);
    public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService);
}
