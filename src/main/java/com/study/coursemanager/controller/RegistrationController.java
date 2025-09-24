package com.study.coursemanager.controller;

import com.study.coursemanager.dto.RegistrationDTO;
import com.study.coursemanager.model.Registration;
import com.study.coursemanager.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
