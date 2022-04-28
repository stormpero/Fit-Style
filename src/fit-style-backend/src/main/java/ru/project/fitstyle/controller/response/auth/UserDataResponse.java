package ru.project.fitstyle.controller.response.auth;

import ru.project.fitstyle.model.entity.user.Role;

import java.util.List;

public class UserDataResponse {
    private final Long id;
    private final String email;
    private final List<String> roles;

    public UserDataResponse(Long id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
