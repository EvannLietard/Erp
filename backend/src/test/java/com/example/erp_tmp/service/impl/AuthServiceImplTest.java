package com.example.erp_tmp.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Optional;

import com.example.erp_tmp.dto.AuthRequest;
import com.example.erp_tmp.dto.RegisterRequest;
import com.example.erp_tmp.model.User;
import com.example.erp_tmp.model.Role;
import com.example.erp_tmp.repository.RoleRepository;
import com.example.erp_tmp.repository.UserRepository;
import com.example.erp_tmp.security.CustomUserDetailsService;
import com.example.erp_tmp.security.JwtUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_Success() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("testuser");
        request.setPassword("password");

        Role mockRole = new Role("ROLE_USER", "Utilisateur standard");
        mockRole.setId("mock-role-id");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        when(roleRepository.findByName("ROLE_USER"))
            .thenReturn(Optional.of(mockRole));

        String result = authService.register(request);
        assertEquals("User registered successfully", result);

        verify(userRepository).findByUsername("testuser");
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
        verify(roleRepository).findByName("ROLE_USER");
    }

    @Test
    void testRegister_UserAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("existinguser");
        request.setPassword("password");

        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(new User()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.register(request);
        });
        assertEquals("User already exists", exception.getMessage());

        verify(userRepository).findByUsername("existinguser");
        verify(userRepository, never()).save(any());
    }

    @Test
    void testLogin_Success() {
        AuthRequest request = new AuthRequest();
        request.setUsername("testuser");
        request.setPassword("password");

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("encodedPassword")
                .authorities(new ArrayList<>())
                .build();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        // Ne pas mocker userRepository.findByUsername ici
        when(jwtUtil.generateToken(userDetails)).thenReturn("mocked-jwt-token");
        when(customUserDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);

        String token = authService.login(request);
        assertEquals("mocked-jwt-token", token);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(customUserDetailsService).loadUserByUsername("testuser");
        verify(jwtUtil).generateToken(userDetails);
    }

    @Test
    void testLogin_UserNotFound() {
        AuthRequest request = new AuthRequest();
        request.setUsername("unknownuser");
        request.setPassword("password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        // Simulez une exception lors du chargement de l'utilisateur inconnu
        when(customUserDetailsService.loadUserByUsername("unknownuser"))
                .thenThrow(new RuntimeException("User not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.login(request);
        });
        assertEquals("User not found", exception.getMessage());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(customUserDetailsService).loadUserByUsername("unknownuser");
        verify(jwtUtil, never()).generateToken(any());
    }
}
