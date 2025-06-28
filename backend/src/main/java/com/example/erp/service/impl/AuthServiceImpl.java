package com.example.erp.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.erp.dto.AuthRequest;
import com.example.erp.dto.RegisterRequest;
import com.example.erp.exception.UserAlreadyExistsException;
import com.example.erp.model.Role;
import com.example.erp.model.User;
import com.example.erp.repository.RoleRepository;
import com.example.erp.repository.UserRepository;
import com.example.erp.security.CustomUserDetailsService;
import com.example.erp.security.JwtUtil;
import com.example.erp.service.AuthService;

import java.util.Set;

/**
 * Implémentation du service d'authentification {@link AuthService}.
 * <p>
 * Gère l'inscription des utilisateurs (avec vérification de l'existence et assignation du rôle par défaut)
 * et la connexion (authentification et génération du token JWT).
 * </p>
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;  
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RoleRepository roleRepository;

    /**
     * Constructeur avec injection des dépendances nécessaires à l'authentification.
     * @param customUserDetailsService service personnalisé pour charger les utilisateurs
     * @param userRepository repository des utilisateurs
     * @param passwordEncoder encodeur de mots de passe
     * @param authenticationManager manager d'authentification Spring Security
     * @param jwtUtil utilitaire pour la gestion des JWT
     * @param roleRepository repository des rôles
     */
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

    /**
     * Inscrit un nouvel utilisateur après vérification de l'unicité du nom d'utilisateur.
     * Attribue le rôle "ROLE_USER" par défaut.
     * @param request les informations d'inscription
     * @return message de succès
     * @throws UserAlreadyExistsException si l'utilisateur existe déjà
     * @throws RuntimeException si le rôle "ROLE_USER" n'est pas trouvé
     */
    @Override
    public String register(RegisterRequest request) {
        String username = request.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        // Attribution du rôle par défaut
        Role userRole = roleRepository.findByName("ROLE_USER")
            .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoleIds(Set.of(userRole.getId()));
        userRepository.save(user);
        return "User registered successfully";
    }

    /**
     * Authentifie un utilisateur et génère un token JWT si les identifiants sont valides.
     * @param request les informations de connexion
     * @return le token JWT
     */
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

