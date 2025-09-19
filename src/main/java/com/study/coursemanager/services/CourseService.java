package com.study.coursemanager.services;

import com.study.coursemanager.model.Course;
import com.study.coursemanager.model.CourseStatus;
import com.study.coursemanager.model.Role;
import com.study.coursemanager.model.User;
import com.study.coursemanager.repositories.CourseRepository;
import com.study.coursemanager.repositories.UserRepository;
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

    public Course findByName(String code) {
        return courseRepository.findByCode(code);
    }

    public Course saveCourse(Course course) {

        validateCode(course.getCode());

        if (course.getInstructor() == null || course.getInstructor().getId() == null) {
            throw new IllegalArgumentException("Instrutor não informado");
        }

        Optional<User> instructor = userRepository.findById(course.getInstructor().getId());

        if (instructor.isEmpty()) {
            throw new IllegalArgumentException("Instrutor não encontrado com ID: " + course.getInstructor().getId());
        }

        if (instructor.get().getRole() != Role.INSTRUCTOR) {
            throw new IllegalArgumentException("Usuário não tem permissão de instrutor");
        }
        return courseRepository.save(course);
    }

    public Course inactiveCourse(String code) {
        try {
            Course entity = courseRepository.findByCode(code);
            if (entity == null) {
                throw new RuntimeException("Curso não encontrado");
            }
            entity.setStatus(CourseStatus.INACTIVE);
            entity.setInactiveAt(LocalDateTime.now());
            return courseRepository.save(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao inativar curso: " + e.getMessage());
        }
    }

    private void validateCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new IllegalArgumentException("O código não pode ser nulo ou vazio");
        }

        if (!code.matches("^[a-z]+(?:-[a-z]+)*$")) {
            throw new IllegalArgumentException("O código deve conter apenas letras minúsculas e hífens");
        }

        if (code.length() < 4 || code.length() > 10) {
            throw new IllegalArgumentException("O código deve ter entre 4 e 10 caracteres");
        }

        if (courseRepository.existsByCode(code)) {
            throw new IllegalArgumentException("Já existe um curso com este código");
        }
    }
}
