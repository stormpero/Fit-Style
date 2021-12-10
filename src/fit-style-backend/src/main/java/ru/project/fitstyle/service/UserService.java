package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.user.FitUserFullNameDto;
import ru.project.fitstyle.model.dto.user.FitUserDto;
import ru.project.fitstyle.model.dto.user.RoleDto;
import ru.project.fitstyle.model.dto.user.SubscriptionDto;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;

import java.util.List;

public interface UserService {

    void validateEmail(final String email);

    FitUser getUserByEmail(final String email);

    List<FitUserDto> getAllUsers();

    void saveUser(FitUser fitUser, final List<Role> roles, final Subscription subscription);

    void changeBalance(FitUser fitUser, final Long summary);

    void disableUser(Long id);

    void enableUser(Long id);

    void logoutUserByEmail(final String email);

    FitUserDto getFitUserInfoByEmail(final String email);

    FitUserDto getFitUserInfoById(final Long id);

    SubscriptionDto getSubscriptionInfoByEmail(final String email);

    SubscriptionDto getSubscriptionInfoById(final Long id);

    List<RoleDto> getUserRolesByEmail(final String email);

    List<RoleDto> getUserRolesById(final Long id);

    List<FitUserFullNameDto> getCoaches();
}
