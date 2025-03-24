package com.project.cyberedge.controller;


import com.project.cyberedge.dto.ApiResponse;
import com.project.cyberedge.dto.CourseDTO;
import com.project.cyberedge.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor/course")
public class CourseAPI {

    private final CourseService courseService;

    @PostMapping("create")
    public ResponseEntity<ApiResponse<Void>> createCourse(@RequestBody CourseDTO.CourseRequest courseRequest) {
        courseService.createCourse(courseRequest);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(200)
                .message("Course Created Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("all")
    public ResponseEntity<ApiResponse<List<CourseDTO.CourseResponse>>> getAllCourses() {
        ApiResponse<List<CourseDTO.CourseResponse>> response = ApiResponse.<List<CourseDTO.CourseResponse>>builder()
                .success(true)
                .status(200)
                .data(courseService.getAllCourses())
                .message("Course Retrieved Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .success(true)
                .status(200)
                .message("Course Deleted Successfully!")
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
