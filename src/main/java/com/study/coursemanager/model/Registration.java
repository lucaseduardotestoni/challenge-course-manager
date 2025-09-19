package com.study.coursemanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_registration", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"}))
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userID;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course courseCode;
    @Column(nullable = false)
    private LocalDateTime enrolledAt;

    public Registration(){}

}
