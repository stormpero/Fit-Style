package ru.project.fitstyle.payload.response.permission;


import ru.project.fitstyle.models.Role;

import java.util.Set;

public class PermissionResponse {
    private Set<Role> roles;

    public PermissionResponse(Set<Role> roles) {
        this.roles = roles;
    }

    public PermissionResponse() {
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
