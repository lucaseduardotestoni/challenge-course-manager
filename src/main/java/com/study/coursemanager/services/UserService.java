package com.study.coursemanager.services;

import com.study.coursemanager.dto.UserDTO;
import com.study.coursemanager.model.enums.Role;
import com.study.coursemanager.model.User;
import com.study.coursemanager.repositories.UserRepository;
import com.study.coursemanager.services.exeptions.BusinessException;
import com.study.coursemanager.services.exeptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Slice<User> listUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return userRepository.findAll(pageable);
    }

    public User newStudent(UserDTO userDTO) {
        User user = new User(userDTO.getname(), userDTO.getEmail(), Role.STUDENT);
        return userRepository.save(user);
    }

    public User newInstructor(UserDTO userDTO) {
        User user = new User(userDTO.getname(), userDTO.getEmail(), Role.INSTRUCTOR);
        return userRepository.save(user);
    }
    @Transactional
    public User changeRole(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(userId));

        if (user.getRole() == Role.ADMIN) {
            throw new BusinessException("Não é permitido alterar o papel de um admin aqui");
        }

        if (user.getRole() == Role.STUDENT) {
            user.setRole(Role.INSTRUCTOR);
        } else if (user.getRole() == Role.INSTRUCTOR) {
            user.setRole(Role.STUDENT);
        }

        return userRepository.save(user);
    }

}
