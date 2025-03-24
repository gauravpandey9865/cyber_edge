package com.project.cyberedge.repository;

import com.project.cyberedge.model.InstructorCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorCourseRepository extends JpaRepository<InstructorCourse, Integer> {
}
