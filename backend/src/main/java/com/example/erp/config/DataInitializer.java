package com.example.erp.config;

import com.example.erp.model.Role;
import com.example.erp.model.User;
import com.example.erp.repository.RoleRepository;
import com.example.erp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initSuperAdmin();
    }

    private void initRoles() {
        List<String> baseRoles = List.of(
            "ROLE_SUPER_ADMIN",
            "ROLE_COMPANY_ADMIN",
            "ROLE_SITE_MANAGER",
            "ROLE_HR_MANAGER",
            "ROLE_ACCOUNTANT",
            "ROLE_PURCHASING_MANAGER",
            "ROLE_SALES_MANAGER",
            "ROLE_STOCK_MANAGER",
            "ROLE_EMPLOYEE"
        );

        for (String roleName : baseRoles) {
            if (!roleRepository.existsByName(roleName)) {
                roleRepository.save(new Role(roleName));
            }
        }
    }

    private void initSuperAdmin() {
        Optional<User> existing = userRepository.findByUsernameAndCompanyIdIsNull("superadmin");
        if (existing.isEmpty()) {
            Role superAdminRole = roleRepository.findByName("ROLE_SUPER_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_SUPER_ADMIN missing"));

            User superAdmin = new User();
            superAdmin.setUsername("superadmin");
            String superAdminPassword = System.getenv().getOrDefault("SUPERADMIN_PASSWORD", "changeMeNow123!");
            superAdmin.setPassword(passwordEncoder.encode(superAdminPassword));
            superAdmin.setRoleIds(Set.of(superAdminRole.getId()));
            superAdmin.setCompanyId(null); // super admin global, pas rattaché à une société

            userRepository.save(superAdmin);
        }
    }
}
