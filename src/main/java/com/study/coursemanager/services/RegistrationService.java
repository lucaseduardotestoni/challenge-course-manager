package com.study.coursemanager.services;

import com.study.coursemanager.dto.RegistrationDTO;
import com.study.coursemanager.model.Course;
import com.study.coursemanager.model.Registration;
import com.study.coursemanager.model.User;
import com.study.coursemanager.repositories.CourseRepository;
import com.study.coursemanager.repositories.RegistrationRepository;
import com.study.coursemanager.repositories.UserRepository;
import com.study.coursemanager.services.exeptions.BusinessException;
import com.study.coursemanager.services.exeptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    CourseRepository courseRepository ;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public Registration saveRegistration(RegistrationDTO registrationDTO) {
        User user = userRepository.findById(registrationDTO.getUser())
                .orElseThrow(() -> new ResourceNotFoundException("User not Found: " + registrationDTO.getUser()));

        Course course = courseRepository.findByCode(registrationDTO.getCourse())
                .filter(c -> c.getStatus() == com.study.coursemanager.model.enums.CourseStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Course not Found or Inactive: " + registrationDTO.getCourse()));

        if (registrationRepository.existsByUser_IdAndCourse_Code(user.getId(), course.getCode())) {
            throw new BusinessException("User already registered in this course");
        }

        Registration registrationNew = new Registration();
        registrationNew.setUserID(user);
        registrationNew.setCourseCode(course);
        registrationNew.setEnrollment_date(LocalDateTime.now());

        return registrationRepository.save(registrationNew);
    }
}
