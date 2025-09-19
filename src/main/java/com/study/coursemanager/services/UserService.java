package com.study.coursemanager.services;

import com.study.coursemanager.model.User;
import com.study.coursemanager.repositories.UserRepository;
import com.study.coursemanager.services.exeptions.InvalidUserDataExeption;
import com.study.coursemanager.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    public User save(User user) {
        if (user.getId() != null) {
            throw new InvalidUserDataExeption("The ID must not be provided when creating a new user.");
        }
        return userRepository.save(user);
    }

}
