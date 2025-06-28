package com.example.erp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.erp.model.User;
import com.example.erp.repository.RoleRepository;
import com.example.erp.repository.UserRepository;

/**
 * Service personnalisé pour charger les détails d'un utilisateur à partir de la base MongoDB.
 * <p>
 * Implémente {@link UserDetailsService} pour l'intégration avec Spring Security.
 * Récupère l'utilisateur par son nom d'utilisateur et construit un {@link UserDetails}
 * avec ses rôles (convertis en {@link SimpleGrantedAuthority}).
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Constructeur avec injection des repositories utilisateur et rôle.
     * @param userRepository repository des utilisateurs
     * @param roleRepository repository des rôles
     */
    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Charge un utilisateur par son nom d'utilisateur et construit un UserDetails Spring Security.
     * @param username le nom d'utilisateur
     * @return UserDetails avec username, mot de passe et rôles
     * @throws UsernameNotFoundException si l'utilisateur n'existe pas
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        // Récupère les rôles à partir des IDs
        var roles = roleRepository.findAllById(user.getRoleIds());

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList()
        );
    }
}
