package com.project.cyberedge.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Course extends AbstractEntity {

    @Column(name = "course_name",nullable = false)
    private String courseName;

    @Column(name = "course_code",nullable = false)
    private String courseCode;

    @Column(name = "credit",nullable = false)
    private Integer credit;

    @Column(name = "is_active",nullable = false)
    private Boolean isActive;
}
