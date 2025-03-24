package com.project.cyberedge.dto;


import com.project.cyberedge.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private RoleEnum role;
}
