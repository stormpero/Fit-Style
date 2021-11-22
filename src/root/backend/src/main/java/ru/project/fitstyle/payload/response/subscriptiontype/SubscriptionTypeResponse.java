package ru.project.fitstyle.payload.response.subscriptiontype;

import ru.project.fitstyle.model.subscription.SubscriptionType;

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
