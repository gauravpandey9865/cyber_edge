package com.project.cyberedge.controller;


import com.project.cyberedge.dto.ApiResponse;
import com.project.cyberedge.dto.RegisterDTO;
import com.project.cyberedge.service.AuthenticationService;
import com.project.cyberedge.url.UrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(UrlConstant.ADMIN)
public class RegistrationAPI {

    private final AuthenticationService authenticationService;

    @PostMapping(UrlConstant.REGISTER)
    public ResponseEntity<ApiResponse<Void>> login(@RequestBody RegisterDTO registerDTO){
        authenticationService.register(registerDTO);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(200)
                .message("User Registered Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
