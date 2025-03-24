package com.project.cyberedge.loader;

import com.project.cyberedge.enums.RoleEnum;
import com.project.cyberedge.model.Role;
import com.project.cyberedge.model.User;
import com.project.cyberedge.repository.RoleRepository;
import com.project.cyberedge.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void init() {
        // Check if the roles table is empty
        if (roleRepository.count() == 0) {
            List<Role> roles = new ArrayList<>();
            roles.add(Role.builder().roleName(RoleEnum.ADMIN).build());
            roles.add(Role.builder().roleName(RoleEnum.INSTRUCTOR).build());
            roles.add(Role.builder().roleName(RoleEnum.STUDENT).build());
            roleRepository.saveAll(roles);
        }

        //Check if user table is empty, add admin
        if(userRepository.count() == 0) {
            Role adminRole = roleRepository.findRoleByRoleName(RoleEnum.ADMIN).orElseThrow(()-> new RuntimeException("Role not found"));
            User adminUser = User.builder()
                    .email("cyberadmin@gmail.com")
                    .password(passwordEncoder.encode("admin123"))
                    .isActive(true)
                    .firstName("CyberEdge")
                    .lastName("Admin")
                    .username("cyberedge")
                    .roles(List.of(adminRole))
                    .build();
            userRepository.save(adminUser);
        }
    }
}
