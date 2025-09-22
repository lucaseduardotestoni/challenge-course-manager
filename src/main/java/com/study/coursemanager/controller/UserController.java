package com.study.coursemanager.controller;

import com.study.coursemanager.dto.UpdateRoleDTO;
import com.study.coursemanager.dto.UserDTO;
import com.study.coursemanager.model.User;
import com.study.coursemanager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("cadastrar")
    public ResponseEntity<User> cadastrar(@RequestBody UserDTO userDTO) {
        User novoUser = userService.save(userDTO);
        System.out.println(novoUser);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoUser.getId()).toUri();
        return ResponseEntity.created(uri).body(novoUser);
    }

    @PutMapping("{id}/role")
    public ResponseEntity<UserDTO> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoleDTO role) {
        User updatedUser = userService.updateRole(id, role.getRole());
        return ResponseEntity.ok(new UserDTO(updatedUser));
    }

}
