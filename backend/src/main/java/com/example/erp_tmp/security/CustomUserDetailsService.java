package com.example.erp_tmp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.erp_tmp.model.User;
import com.example.erp_tmp.repository.RoleRepository;
import com.example.erp_tmp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository; // Ajoute ce repository

    public CustomUserDetailsService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

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
