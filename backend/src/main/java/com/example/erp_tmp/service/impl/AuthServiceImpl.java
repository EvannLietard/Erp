package com.example.erp_tmp.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import com.example.erp_tmp.dto.AuthRequest;
import com.example.erp_tmp.dto.RegisterRequest;
import com.example.erp_tmp.exception.UserAlreadyExistsException;
import com.example.erp_tmp.model.Role;
import com.example.erp_tmp.model.User;
import com.example.erp_tmp.repository.RoleRepository;
import com.example.erp_tmp.repository.UserRepository;
import com.example.erp_tmp.security.CustomUserDetailsService;
import com.example.erp_tmp.security.JwtUtil;
import com.example.erp_tmp.service.AuthService;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;  
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final    RoleRepository roleRepository;

    public AuthServiceImpl(CustomUserDetailsService customUserDetailsService,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           RoleRepository roleRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.roleRepository = roleRepository;
    }
    @Override
    public String register(RegisterRequest request) {
        String username = request.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Si tu as une entité Role et un repository
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoleIds(Set.of(userRole.getId()));
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

