package com.study.coursemanager.services;

import com.study.coursemanager.dto.CourseDTO;
import com.study.coursemanager.model.Course;
import com.study.coursemanager.model.enums.CourseStatus;
import com.study.coursemanager.model.enums.Role;
import com.study.coursemanager.model.User;
import com.study.coursemanager.repositories.CourseRepository;
import com.study.coursemanager.repositories.UserRepository;
import com.study.coursemanager.services.exeptions.BusinessException;
import com.study.coursemanager.services.exeptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public Course saveCourse(CourseDTO course) {
        validateCode(course.getCode());

        User instructor = userRepository.findById(course.getInstructorId())
                .filter(u -> u.getRole() == Role.INSTRUCTOR)
                .orElseThrow(() -> new BusinessException("Instructor not found or invalid"));

        Course newCourse = new Course(
                course.getCode(),
                course.getName(),
                course.getDescription(),
                instructor,
                LocalDateTime.now()
        );
        return courseRepository.save(newCourse);
    }

    @Transactional
    public Course inactiveCourse(String code) {
        Course entity = courseRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(code));

            if (entity.getStatus() == CourseStatus.INACTIVE) {
                throw new BusinessException("Course is already inactive");
            }
            entity.setStatus(CourseStatus.INACTIVE);
            entity.setInactivation_date(LocalDateTime.now());
            return courseRepository.save(entity);
    }

    private void validateCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new BusinessException("The code not is null or empty");
        }

        if (!code.matches("^[a-z]+(?:-[a-z]+)*$")) {
            throw new BusinessException("The code must be lowercase and can contain hyphens");
        }

        if (code.length() < 4 || code.length() > 10) {
            throw new BusinessException("The code must be between 4 and 10 characters");
        }

        if (courseRepository.existsByCode(code)) {
            throw new BusinessException("This alerady exists course code" );
        }
    }
}
