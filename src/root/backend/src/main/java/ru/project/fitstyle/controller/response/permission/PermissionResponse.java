package ru.project.fitstyle.controller.response.permission;

import ru.project.fitstyle.model.dto.user.Role;

import java.util.List;

public class PermissionResponse {
    private final List<Role> roles;

    public PermissionResponse(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }
}
