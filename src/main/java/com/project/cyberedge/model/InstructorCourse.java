package com.project.cyberedge.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instructor_course")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class InstructorCourse extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "course_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_course", value = ConstraintMode.CONSTRAINT))
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_user", value = ConstraintMode.CONSTRAINT))
    private User instructor;

}
