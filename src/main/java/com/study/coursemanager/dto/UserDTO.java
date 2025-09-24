package com.study.coursemanager.dto;

import com.study.coursemanager.model.User;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String email;

    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getname() {
        return name;
    }

    public void setName(String username) {this.name = username;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
