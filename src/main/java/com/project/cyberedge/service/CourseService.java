package com.project.cyberedge.service;


import com.project.cyberedge.dto.CourseDTO;
import com.project.cyberedge.dto.UserDTO;
import com.project.cyberedge.model.Course;
import com.project.cyberedge.model.InstructorCourse;
import com.project.cyberedge.model.User;
import com.project.cyberedge.repository.CourseRepository;
import com.project.cyberedge.repository.InstructorCourseRepository;
import com.project.cyberedge.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstructorCourseRepository instructorCourseRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createCourse(CourseDTO.CourseRequest courseRequest) {
        Course course = Course.builder()
                              .courseName(courseRequest.getCourseName())
                              .courseCode(courseRequest.getCourseCode())
                              .credit(courseRequest.getCredit())
                              .isActive(true)
                              .build();
        Course savedCourse = courseRepository.save(course);
        if(!courseRequest.getInstructorIds().isEmpty()){
            List<User> instructors = userRepository.findAllById(courseRequest.getInstructorIds());
            List<InstructorCourse> instructorCourses = instructors.stream().map(instructor-> new InstructorCourse(savedCourse,instructor))
                    .toList();
            instructors.forEach(instructor -> {
               instructorCourseRepository.saveAll(instructorCourses);
            });
        }
    }

    public List<CourseDTO.CourseResponse> getAllCourses() {
        return instructorCourseRepository.findAll().stream()
                .filter(instructorCourse -> instructorCourse.getCourse().getIsActive())
                .collect(Collectors.groupingBy(
                        InstructorCourse::getCourse,
                        Collectors.mapping(InstructorCourse::getInstructor, Collectors.toList())
                ))
                .entrySet().stream()
                .map(this::mapToCourseResponse)
                .toList();
    }

    private CourseDTO.CourseResponse mapToCourseResponse(Map.Entry<Course, List<User>> entry) {
        Course course = entry.getKey();
        List<UserDTO.UserResponseDTO> instructors = entry.getValue().stream()
                .map(UserDTO.UserResponseDTO::from)
                .toList();
        return new CourseDTO.CourseResponse(
                course.getId(),
                course.getCourseName(),
                course.getCourseCode(),
                course.getCredit(),
                instructors
        );
    }

    public void deleteCourse(Integer id){
        Course course = courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course not found"));
        course.setIsActive(false);
        courseRepository.save(course);
    }
}
