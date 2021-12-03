package ru.project.fitstyle.controller.response.profile;

import ru.project.fitstyle.model.dto.user.FitUserInfo;
import ru.project.fitstyle.model.dto.user.RoleInfo;
import ru.project.fitstyle.model.dto.user.SubscriptionResponseInfo;

import java.util.Date;
import java.util.List;

public class UserProfileResponse {

    private final FitUserInfo fitUserInfo;

    private final SubscriptionResponseInfo subscriptionResponseInfo;

    private final List<RoleInfo> roles;

    public UserProfileResponse(FitUserInfo fitUserInfo, SubscriptionResponseInfo subscriptionResponseInfo, List<RoleInfo> roles) {
        this.fitUserInfo = fitUserInfo;
        this.subscriptionResponseInfo = subscriptionResponseInfo;
        this.roles = roles;
    }

    public FitUserInfo getFitUserInfo() {
        return fitUserInfo;
    }

    public SubscriptionResponseInfo getSubscriptionResponseInfo() {
        return subscriptionResponseInfo;
    }

    public List<RoleInfo> getRoles() {
        return roles;
    }
}
