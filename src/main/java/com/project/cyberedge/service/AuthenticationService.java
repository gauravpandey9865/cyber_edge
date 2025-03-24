package com.project.cyberedge.service;


import com.project.cyberedge.dto.LoginDTO;
import com.project.cyberedge.dto.LoginResponse;
import com.project.cyberedge.dto.RegisterDTO;
import com.project.cyberedge.model.Role;
import com.project.cyberedge.model.User;
import com.project.cyberedge.repository.RoleRepository;
import com.project.cyberedge.repository.UserRepository;
import com.project.cyberedge.utils.JwtUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtilities jwtUtilities;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public LoginResponse authenticate(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsername(),
                        loginDTO.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userRepository.findUserByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        user.getRoles().forEach(r -> rolesNames.add(r.getRoleName().name()));
        String token = jwtUtilities.generateToken(user.getUsername(), rolesNames);
        return new LoginResponse(token);
    }

    public void register(RegisterDTO registerDTO){
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setIsActive(true);
        Role role = roleRepository.findRoleByRoleName(registerDTO.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(List.of(role));

        userRepository.save(user);

    }
}
