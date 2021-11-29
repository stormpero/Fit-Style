package ru.project.fitstyle.payload.response.permission;

import ru.project.fitstyle.model.dto.user.Role;

import java.util.Set;

public class PermissionResponse {
    private final Set<Role> roles;

    public PermissionResponse(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
