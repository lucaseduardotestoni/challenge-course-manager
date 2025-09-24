package com.study.coursemanager.controller;

import com.study.coursemanager.dto.CourseEnrollmentDTO;
import com.study.coursemanager.dto.RegistrationDTO;
import com.study.coursemanager.model.Registration;
import com.study.coursemanager.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/registration")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @PostMapping(value = "/new")
    public ResponseEntity<Registration> registerUser(@Valid @RequestBody RegistrationDTO registrationDTO) {
        Registration registration = registrationService.saveRegistration(registrationDTO);
        return ResponseEntity.ok(registration);
    }
    @GetMapping(value = "/report")
    public ResponseEntity<List<CourseEnrollmentDTO>> getMostEnrolledCourses(
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(registrationService.getMostEnrolledCourses(limit));
    }
}
