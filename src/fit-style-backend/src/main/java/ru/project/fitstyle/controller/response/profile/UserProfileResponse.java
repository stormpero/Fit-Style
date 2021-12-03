package ru.project.fitstyle.controller.response.profile;

import ru.project.fitstyle.model.dto.user.FitUserInfo;
import ru.project.fitstyle.model.dto.user.RoleInfo;
import ru.project.fitstyle.model.dto.user.SubscriptionInfo;

import java.util.List;

public class UserProfileResponse {

    private final FitUserInfo fitUserInfo;

    private final SubscriptionInfo subscriptionInfo;

    private final List<RoleInfo> roles;

    public UserProfileResponse(FitUserInfo fitUserInfo, SubscriptionInfo subscriptionInfo, List<RoleInfo> roles) {
        this.fitUserInfo = fitUserInfo;
        this.subscriptionInfo = subscriptionInfo;
        this.roles = roles;
    }

    public FitUserInfo getFitUserInfo() {
        return fitUserInfo;
    }

    public SubscriptionInfo getSubscriptionInfo() {
        return subscriptionInfo;
    }

    public List<RoleInfo> getRoles() {
        return roles;
    }
}
