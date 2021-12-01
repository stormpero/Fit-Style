package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.subscription.Subscription;
import ru.project.fitstyle.model.dto.user.ERole;
import ru.project.fitstyle.model.dto.user.FitUser;
import ru.project.fitstyle.model.dto.user.Role;

import java.util.List;
import java.util.Set;

public interface UserService {

    void validateEmail(String email);

    FitUser getUserByEmail(String email);

    FitUser getUserById(Long id);

    FitUser getCoachById(Long id);

    void saveFitUser(FitUser fitUser, List<Role> roles, Subscription subscription);

    void changeBalance(FitUser fitUser, Long summary);

    void logoutFitUserByEmail(String email);

    List<Role> getFitUserRolesByEmail(String email);
}
