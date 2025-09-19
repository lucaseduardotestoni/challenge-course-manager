package com.study.coursemanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table (name = "tb_courses")
public class Course {
    @Id
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = "^[a-z]+(?:-[a-z]+)*$")
    @Size(min = 4, max = 10)
    private String code;
    private String name;
    private String description;
    @ManyToOne
    private User instructor;
    private CourseStatus status;
    private LocalDateTime inactiveAt;

    public Course(){
        this.status = CourseStatus.ACTIVE;
        this.inactiveAt = null;
    }

    public Course(String code, String name, String description, User instructor) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.instructor = instructor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public LocalDateTime getInactiveAt() {
        return inactiveAt;
    }

    public void setInactiveAt(LocalDateTime inactiveAt) {
        this.inactiveAt = inactiveAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

}