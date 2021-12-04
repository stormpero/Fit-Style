package ru.project.fitstyle.controller.response.profile;

import ru.project.fitstyle.model.dto.user.FitUserDto;
import ru.project.fitstyle.model.dto.user.RoleDto;
import ru.project.fitstyle.model.dto.user.SubscriptionDto;

import java.util.List;

public class UserProfileResponse {

    private final FitUserDto fitUserInfo;

    private final SubscriptionDto subscriptionInfo;

    private final List<RoleDto> roles;

    public UserProfileResponse(FitUserDto fitUserInfo, SubscriptionDto subscriptionInfo, List<RoleDto> roles) {
        this.fitUserInfo = fitUserInfo;
        this.subscriptionInfo = subscriptionInfo;
        this.roles = roles;
    }

    public FitUserDto getFitUserInfo() {
        return fitUserInfo;
    }

    public SubscriptionDto getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }
}
