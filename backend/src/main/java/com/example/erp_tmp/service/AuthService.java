package com.example.erp_tmp.service;

import com.example.erp_tmp.dto.AuthRequest;
import com.example.erp_tmp.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    String login(AuthRequest request);
}
