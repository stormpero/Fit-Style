package ru.project.fitstyle.model.dto.user;

public class RoleDto {
    private final Long id;

    private final String name;

    public RoleDto(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
