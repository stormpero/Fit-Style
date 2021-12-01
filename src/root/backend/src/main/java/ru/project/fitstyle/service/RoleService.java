package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.user.ERole;
import ru.project.fitstyle.model.dto.user.FitUser;
import ru.project.fitstyle.model.dto.user.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    List<Role> createFitRoles(List<String> strRoles);

    List<FitUser> getUserByRole(ERole role);
}
