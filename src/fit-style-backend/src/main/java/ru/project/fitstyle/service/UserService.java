package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.user.*;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.entity.user.Role;

import java.util.List;

public interface UserService {

    void validateEmailForRegister(final String email);

    FitUser getUserByEmail(final String email);

    FitUser getUserById(final Long id);

    List<FitUserFullInfoDto> getAllUsers();

    void saveUser(FitUser fitUser, final List<Role> roles, final Subscription subscription);

    void changeBalance(FitUser fitUser, final Long summary);

    void changePassword(final Long id, final String password);

    void disableUser(Long id);

    void enableUser(Long id);

    void logoutUserByEmail(final String email);

    void roleAssign(Long userId, Long roleId);

    FitUserDto getFitUserInfoByEmail(final String email);

    FitUserDto getFitUserInfoById(final Long id);

    SubscriptionDto getSubscriptionInfoByEmail(final String email);

    SubscriptionDto getSubscriptionInfoById(final Long id);

    List<RoleDto> getUserRolesByEmail(final String email);

    List<RoleDto> getUserRolesById(final Long id);

    List<FitUserFullNameDto> getCoaches();
}
