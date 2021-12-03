package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.user.CoachInfo;
import ru.project.fitstyle.model.dto.user.FitUserInfo;
import ru.project.fitstyle.model.dto.user.RoleInfo;
import ru.project.fitstyle.model.dto.user.SubscriptionInfo;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;

import java.util.List;

public interface UserService {

    void validateEmail(String email);

    FitUser getUserByEmail(String email);

    void saveUser(FitUser fitUser, List<Role> roles, Subscription subscription);

    void changeBalance(FitUser fitUser, Long summary);

    void logoutUserByEmail(String email);

    FitUserInfo getFitUserInfoByEmail(String email);

    FitUserInfo getFitUserInfoById(Long id);

    SubscriptionInfo getSubscriptionResponseInfoByEmail(String email);

    SubscriptionInfo getSubscriptionResponseInfoById(Long id);

    List<RoleInfo> getUserRolesByEmail(String email);

    List<RoleInfo> getUserRolesById(Long id);

    List<CoachInfo> getCoaches();
}
