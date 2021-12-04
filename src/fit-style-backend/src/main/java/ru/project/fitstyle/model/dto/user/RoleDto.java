package ru.project.fitstyle.model.dto.user;

import ru.project.fitstyle.model.entity.user.ERole;

public class RoleDto {
    private final Long id;

    private final ERole name;

    public RoleDto(Long id, ERole name) {
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
