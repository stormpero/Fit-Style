package ru.project.fitstyle.service;

import ru.project.fitstyle.model.entity.user.ERole;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;

import java.util.List;

public interface RoleService {

    List<Role> createFitRoles(List<String> strRoles);

    List<FitUser> getUserByRole(ERole role);
}
