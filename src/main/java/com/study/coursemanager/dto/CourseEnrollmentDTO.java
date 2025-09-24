package com.study.coursemanager.dto;

import com.study.coursemanager.model.Course;

public class CourseEnrollmentDTO {

    private String courseCode;
    private String courseName;
    private Long TotalEnrollment;

    public CourseEnrollmentDTO() {}

    public CourseEnrollmentDTO(String courseCode,String courseName, Long totalEnrollments) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.TotalEnrollment = totalEnrollments;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getTotalenrollment() {
        return TotalEnrollment;
    }

    public void setTotalenrollment(Long totalenrollment) {
        TotalEnrollment = totalenrollment;
    }
}
