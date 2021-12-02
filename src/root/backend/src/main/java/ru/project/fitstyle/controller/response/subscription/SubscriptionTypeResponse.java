package ru.project.fitstyle.controller.response.subscription;

import ru.project.fitstyle.model.entity.subscription.SubscriptionType;

import java.util.List;

public class SubscriptionTypeResponse {
    private final List<SubscriptionType> subscriptionTypes;

    public SubscriptionTypeResponse(List<SubscriptionType> subscriptionTypes) {
        this.subscriptionTypes = subscriptionTypes;
    }

    public List<SubscriptionType> getSubscriptionTypes() {
        return subscriptionTypes;
    }
}
