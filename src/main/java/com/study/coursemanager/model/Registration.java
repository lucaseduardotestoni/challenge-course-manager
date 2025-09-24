package com.study.coursemanager.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_registration", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_code"}))
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_code", referencedColumnName = "code")
    private Course course;

    private LocalDateTime enrollment_date;

    public Registration(){

    }

    public Registration(User user, Course course, LocalDateTime enrollment_date) {
        this.user = user;
        this.course = course;
        this.enrollment_date = enrollment_date;
    }

    public Long getId() {
        return id;
    }

    public User getUserID() {
        return user;
    }

    public void setUserID(User userID) {
        this.user = userID;
    }

    public Course getCourseCode() {
        return course;
    }

    public void setCourseCode(Course courseCode) {
        this.course = courseCode;
    }

    public LocalDateTime getEnrollment_date() {
        return enrollment_date;
    }

    public void setEnrollment_date(LocalDateTime enrolledAt) {
        this.enrollment_date = enrolledAt;
    }
}
