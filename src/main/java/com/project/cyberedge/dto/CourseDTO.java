package com.project.cyberedge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {

    @Data
    public static class CourseRequest{
        private String courseName;
        private String courseCode;
        private Integer credit;
        private List<Integer> instructorIds;
    }

    @Data
    @AllArgsConstructor
    public static class CourseResponse {
        private Integer id;
        private String courseName;
        private String courseCode;
        private Integer credit;
        private List<UserDTO.UserResponseDTO> instructors;

    }
}
