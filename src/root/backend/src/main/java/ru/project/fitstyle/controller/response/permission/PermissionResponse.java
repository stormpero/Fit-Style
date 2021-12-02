package ru.project.fitstyle.controller.response.permission;

import java.util.List;

public class PermissionResponse {
    private final List<RoleInfo> roles;

    public PermissionResponse(List<RoleInfo> roles) {
        this.roles = roles;
    }

    public List<RoleInfo> getRoles() {
        return roles;
    }
}
