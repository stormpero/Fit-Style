package ru.project.fitstyle.json.response;

import java.util.List;

public class AllRolesResponse {
    private final List<RoleDto> roles;

    public AllRolesResponse(List<RoleDto> roles) {
        this.roles = roles;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }



    public static class RoleDto {
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
}