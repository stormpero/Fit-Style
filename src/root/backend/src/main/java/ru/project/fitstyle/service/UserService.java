package ru.project.fitstyle.service;

import ru.project.fitstyle.controller.response.permission.RoleInfo;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;

import java.util.List;

public interface UserService {

    void validateEmail(String email);

    FitUser getUserByEmail(String email);

    FitUser getUserById(Long id);

    FitUser getCoachById(Long id);

    void saveFitUser(FitUser fitUser, List<Role> roles, Subscription subscription);

    void changeBalance(FitUser fitUser, Long summary);

    void logoutFitUserByEmail(String email);

    List<RoleInfo> getFitUserRolesByEmail(String email);

    List<RoleInfo> getFitUserRolesById(Long id);
}
