package com.study.coursemanager.services;

import com.study.coursemanager.dto.CourseDTO;
import com.study.coursemanager.model.Course;
import com.study.coursemanager.model.CourseStatus;
import com.study.coursemanager.model.Role;
import com.study.coursemanager.model.User;
import com.study.coursemanager.repositories.CourseRepository;
import com.study.coursemanager.repositories.UserRepository;
import com.study.coursemanager.services.exeptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    public Course findByCode(String code) {
        return courseRepository.findByCode(code);
    }

    public Course saveCourse(CourseDTO course) {
        validateCode(course.getCode());
        validateInstructor(course.getInstructorId());

        User instructor = userRepository.findById(course.getInstructorId())
                .orElseThrow(() -> new BusinessException("Instrutor não encontrado com ID: " + course.getInstructorId()));

        if (instructor.getRole() != Role.INSTRUCTOR) {
            throw new BusinessException("Usuário não tem permissão de instrutor");
        }

        Course newCourse = new Course(
                course.getCode(),
                course.getName(),
                course.getDescription(),
                instructor
        );
        return courseRepository.save(newCourse);
    }

    private void validateInstructor(Long instructorId) {
        if (instructorId == null) {
            throw new BusinessException("Instrutor não informado");
        }
    }

    public Course inactiveCourse(String code) {
        Course entity = Optional.ofNullable(courseRepository.findByCode(code))
                .orElseThrow(() -> new BusinessException("Curso não encontrado"));

            if (entity.getStatus() == CourseStatus.INACTIVE) {
                throw new BusinessException("Curso já está inativo");
            }
            entity.setStatus(CourseStatus.INACTIVE);
            entity.setInactiveAt(LocalDateTime.now());
            return courseRepository.save(entity);
    }

    private void validateCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new BusinessException("O código não pode ser nulo ou vazio");
        }

        if (!code.matches("^[a-z]+(?:-[a-z]+)*$")) {
            throw new BusinessException("O código deve conter apenas letras minúsculas e hífens");
        }

        if (code.length() < 4 || code.length() > 10) {
            throw new BusinessException("O código deve ter entre 4 e 10 caracteres");
        }

        if (courseRepository.existsByCode(code)) {
            throw new BusinessException("Já existe um curso com este código");
        }
    }
}
