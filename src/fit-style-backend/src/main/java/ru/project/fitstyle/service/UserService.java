package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.user.CoachDto;
import ru.project.fitstyle.model.dto.user.FitUserDto;
import ru.project.fitstyle.model.dto.user.RoleDto;
import ru.project.fitstyle.model.dto.user.SubscriptionDto;
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

    FitUserDto getFitUserInfoByEmail(String email);

    FitUserDto getFitUserInfoById(Long id);

    SubscriptionDto getSubscriptionResponseInfoByEmail(String email);

    SubscriptionDto getSubscriptionResponseInfoById(Long id);

    List<RoleDto> getUserRolesByEmail(String email);

    List<RoleDto> getUserRolesById(Long id);

    List<CoachDto> getCoaches();
}
