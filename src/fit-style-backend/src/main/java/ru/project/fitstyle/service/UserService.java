package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.user.CoachInfo;
import ru.project.fitstyle.model.dto.user.FitUserInfo;
import ru.project.fitstyle.model.dto.user.RoleInfo;
import ru.project.fitstyle.model.dto.user.SubscriptionResponseInfo;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;

import java.util.List;

public interface UserService {

    void validateEmail(String email);

    FitUser getUserByEmail(String email);

    FitUser getUserById(Long id);

//    FitUser getCoachById(Long id);

    void saveFitUser(FitUser fitUser, List<Role> roles, Subscription subscription);

    void changeBalance(FitUser fitUser, Long summary);

    void logoutFitUserByEmail(String email);

    FitUserInfo getFitUserInfoByEmail(String email);

    FitUserInfo getFitUserInfoById(Long id);

    SubscriptionResponseInfo getSubscriptionResponseInfoByEmail(String email);

    SubscriptionResponseInfo getSubscriptionResponseInfoById(Long id);

    List<RoleInfo> getFitUserRolesByEmail(String email);

    List<RoleInfo> getFitUserRolesById(Long id);

    List<CoachInfo> getAllCoaches();
}
