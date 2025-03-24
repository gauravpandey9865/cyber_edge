package com.project.cyberedge.controller;


import com.project.cyberedge.dto.ApiResponse;
import com.project.cyberedge.dto.LoginDTO;
import com.project.cyberedge.dto.LoginResponse;
import com.project.cyberedge.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationAPI {
    private final AuthenticationService authenticationService;
    @PostMapping("login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginDTO loginDTO){
        ApiResponse<LoginResponse> response = ApiResponse.<LoginResponse>builder()
                .success(true)
                .status(200)
                .message("Successfully authenticated!")
                .data(authenticationService.authenticate(loginDTO))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
