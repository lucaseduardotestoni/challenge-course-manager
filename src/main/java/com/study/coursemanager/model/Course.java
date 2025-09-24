package com.study.coursemanager.model;

import com.study.coursemanager.model.enums.CourseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table (name = "tb_courses")
public class Course {

    @Id
    @Column(length = 10)
    @Pattern(regexp = "^[a-z]+(?:-[a-z]+)*$")
    @Size(min = 4, max = 10)
    private String code;

    private String name;

    private String description;
    @ManyToOne
    private User instructor;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    private LocalDateTime inactivation_date;

    private LocalDateTime creation_date ;

    public LocalDateTime getCreation_date() {
        return creation_date;
    }

    public Course(){
    }

    public Course(String code, String name, String description, User instructor, LocalDateTime creation_date) {
        this.code = code;
        this.name = name;
        this.status = CourseStatus.ACTIVE;
        this.inactivation_date = null;
        this.description = description;
        this.instructor = instructor;
        this.creation_date = creation_date;
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

    public LocalDateTime getInactivation_date() {
        return inactivation_date;
    }

    public void setInactivation_date(LocalDateTime inactivation_date) {
        this.inactivation_date = inactivation_date;
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