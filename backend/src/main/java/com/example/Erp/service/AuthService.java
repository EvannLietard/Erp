package com.example.erp.service;

import com.example.erp.dto.AuthRequest;
import com.example.erp.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    String login(AuthRequest request);
}
