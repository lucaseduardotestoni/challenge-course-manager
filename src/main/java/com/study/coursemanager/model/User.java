package com.study.coursemanager.model;

import com.study.coursemanager.model.enums.Role;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table (name = "tb_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private Role role;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

    public User() {
    }

    public User(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
