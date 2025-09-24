package com.study.coursemanager.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class RegistrationDTO {

    private Long user;

    private String course;

    public RegistrationDTO() {}

    public RegistrationDTO(Long userId, String courseId) {
        this.user = userId;
        this.course = courseId;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
