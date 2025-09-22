package com.study.coursemanager.controller.exeptions;

import com.study.coursemanager.model.Role;
import jakarta.validation.constraints.NotNull;

public class UpdateRoleDTO {
    @NotNull(message = "Role é obrigatória")
    private Role role;

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
