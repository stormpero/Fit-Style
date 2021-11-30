package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.subscription.Subscription;
import ru.project.fitstyle.model.dto.user.ERole;
import ru.project.fitstyle.model.dto.user.FitUser;
import ru.project.fitstyle.model.dto.user.Role;

import java.util.Set;

public interface UserService {

    void validateEmail(String email);

    FitUser getUserByEmail(String email);

    FitUser getUserById(Long id);

    FitUser getCoachById(Long id);

    void saveFitUser(FitUser fitUser, Set<String> strRoles, Subscription subscription);

    void logoutFitUserByEmail(String email);

    Set<Role> getFitUserRolesByEmail(String email);

    Set<Role> createFitUserRoles(Set<String> strRoles);
}
