package ru.project.fitstyle.controller.response.permission;

import ru.project.fitstyle.model.dto.user.RoleDto;

import java.util.List;

public class PermissionResponse {
    private final List<RoleDto> roles;

    public PermissionResponse(List<RoleDto> roles) {
        this.roles = roles;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }
}
