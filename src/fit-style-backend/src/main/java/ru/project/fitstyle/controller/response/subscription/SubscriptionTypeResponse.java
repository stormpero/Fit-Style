package ru.project.fitstyle.controller.response.subscription;

import ru.project.fitstyle.model.dto.subscription.SubscriptionTypeInfo;

import java.util.List;

public class SubscriptionTypeResponse {
    private final List<SubscriptionTypeInfo> subscriptionTypes;

    public SubscriptionTypeResponse(List<SubscriptionTypeInfo> subscriptionTypes) {
        this.subscriptionTypes = subscriptionTypes;
    }

    public List<SubscriptionTypeInfo> getSubscriptionTypes() {
        return subscriptionTypes;
    }
}
