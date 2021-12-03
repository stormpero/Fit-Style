package ru.project.fitstyle.controller.response.profile;

import ru.project.fitstyle.model.dto.user.FitUserInfo;
import ru.project.fitstyle.model.dto.user.RoleInfo;
import ru.project.fitstyle.model.dto.user.SubscriptionResponseInfo;

import java.util.List;

public class UserProfileResponse {

    private final FitUserInfo fitUserInfo;

    private final SubscriptionResponseInfo subscriptionInfo;

    private final List<RoleInfo> roles;

    public UserProfileResponse(FitUserInfo fitUserInfo, SubscriptionResponseInfo subscriptionInfo, List<RoleInfo> roles) {
        this.fitUserInfo = fitUserInfo;
        this.subscriptionInfo = subscriptionInfo;
        this.roles = roles;
    }

    public FitUserInfo getFitUserInfo() {
        return fitUserInfo;
    }

    public SubscriptionResponseInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public List<RoleInfo> getRoles() {
        return roles;
    }
}
