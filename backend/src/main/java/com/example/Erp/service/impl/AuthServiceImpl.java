package com.example.erp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import com.example.erp.service.AuthService;
import com.example.erp.model.User;
import com.example.erp.repository.UserRepository;
import com.example.erp.security.JwtUtil;
import com.example.erp.dto.AuthRequest;
import com.example.erp.dto.RegisterRequest;
import com.example.erp.security.CustomUserDetailsService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;  

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String register(RegisterRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "User registered successfully";
    }

    @Override
    public String login(AuthRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );
        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails);
    }
}

