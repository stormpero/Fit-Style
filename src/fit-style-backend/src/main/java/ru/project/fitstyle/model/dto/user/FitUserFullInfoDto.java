package ru.project.fitstyle.model.dto.user;

import java.util.List;

public class FitUserFullInfoDto {
    private final FitUserDto fitUserInfo;

    private final SubscriptionDto subscriptionInfo;

    private final List<RoleDto> roles;

    public FitUserFullInfoDto(FitUserDto fitUserInfo, SubscriptionDto subscriptionInfo, List<RoleDto> roles) {
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
