package ru.project.fitstyle.json.response;

import java.util.List;

public class AllRolesResponse {
    private List<RoleDto> roles;

    public AllRolesResponse(List<RoleDto> roles) {
        this.roles = roles;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public AllRolesResponse() {
    }


    public static class RoleDto {
        private Long id;

        private String name;

        public RoleDto(final Long id, final String name) {
            this.id = id;
            this.name = name;
        }

        public RoleDto() {
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}