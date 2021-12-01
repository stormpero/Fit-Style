package ru.project.fitstyle.controller.response.permission;

import ru.project.fitstyle.model.dto.user.ERole;

public class RoleInfo {
    private final Long id;

    private final ERole name;

    public RoleInfo(Long id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }
}
