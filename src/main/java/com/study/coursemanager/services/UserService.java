package com.study.coursemanager.services;

import com.study.coursemanager.dto.UserDTO;
import com.study.coursemanager.model.Role;
import com.study.coursemanager.model.User;
import com.study.coursemanager.repositories.UserRepository;
import com.study.coursemanager.services.exeptions.InvalidUserDataExeption;
import com.study.coursemanager.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User save(UserDTO userDTO) {
        User user = new User(userDTO.getname(), userDTO.getEmail());
        return userRepository.save(user);
    }

    public User updateRole(Long id, Role newRole) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        if (user.getRole().equals(newRole)) {
            return user;
        }
        user.setRole(newRole);

        return userRepository.save(user);
    }



}
