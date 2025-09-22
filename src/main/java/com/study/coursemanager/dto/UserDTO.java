package com.study.coursemanager.dto;

import com.study.coursemanager.model.Role;
import com.study.coursemanager.model.User;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String email;

    private Role role;

    public UserDTO() {
    }

    public UserDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public UserDTO(User user) {
        this.name = user.getname();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public Role getRole() { return role; }

    public String getname() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
