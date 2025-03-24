package com.project.cyberedge.dto;


import com.project.cyberedge.model.User;
import lombok.Data;

@Data
public class UserDTO {

    @Data
    public static class UserResponseDTO {
        private Integer id;
        private String firstName;
        private String lastName;
        private String email;

        public static UserResponseDTO from(User user) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setFirstName(user.getFirstName());
            userResponseDTO.setLastName(user.getLastName());
            userResponseDTO.setEmail(user.getEmail());
            return userResponseDTO;
        }
    }
}
