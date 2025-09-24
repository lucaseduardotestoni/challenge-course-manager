package com.study.coursemanager.controller;

import com.study.coursemanager.dto.UserDTO;
import com.study.coursemanager.model.User;
import com.study.coursemanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping("Student/new")
    public ResponseEntity<User> newStudent(@RequestBody UserDTO userDTO) {
        User newStudent = userService.newStudent(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newStudent.getId()).toUri();
        return ResponseEntity.created(uri).body(newStudent);
    }
    @PostMapping("Instructor/new")
    public ResponseEntity<User> newInstructor(@RequestBody UserDTO userDTO) {
        User newStudent = userService.newInstructor(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newStudent.getId()).toUri();
        return ResponseEntity.created(uri).body(newStudent);
    }

    @PutMapping("{id}/toggle-role")
    public ResponseEntity<UserDTO> changeRole(@PathVariable Long id){
        User updatedUser = userService.changeRole(id);
        return ResponseEntity.ok(new UserDTO(updatedUser));
    }
    @GetMapping("/listusers")
    public Slice<User> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.listUsers(page, size);
    }

}
