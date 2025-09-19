package com.study.coursemanager.controller;

import com.study.coursemanager.model.Course;
import com.study.coursemanager.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping(value = "new")
    public ResponseEntity<Course> newCourse(@RequestBody Course course) {
        Course novoCourse = courseService.saveCourse(course);
        return ResponseEntity.ok(novoCourse);
    }

    @PostMapping(value = "{code}/inactive")
    public ResponseEntity<Course> inactive(@PathVariable String code) {
        Course course = courseService.inactiveCourse(code);
        return ResponseEntity.ok(course);
    }
}
