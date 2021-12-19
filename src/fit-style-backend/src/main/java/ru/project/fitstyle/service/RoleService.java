package ru.project.fitstyle.service;

import ru.project.fitstyle.model.entity.user.Role;

import java.util.List;

public interface RoleService {

    List<Role> createRoles(final List<String> strRoles);

    void addRole(final String name);
}
