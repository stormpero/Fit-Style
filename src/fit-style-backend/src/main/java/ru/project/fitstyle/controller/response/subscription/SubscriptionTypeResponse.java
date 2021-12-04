package ru.project.fitstyle.controller.response.subscription;

import ru.project.fitstyle.model.dto.subscription.SubscriptionTypeDto;

import java.util.List;

public class SubscriptionTypeResponse {
    private final List<SubscriptionTypeDto> subscriptionTypes;

    public SubscriptionTypeResponse(List<SubscriptionTypeDto> subscriptionTypes) {
        this.subscriptionTypes = subscriptionTypes;
    }

    public List<SubscriptionTypeDto> getSubscriptionTypes() {
        return subscriptionTypes;
    }
}
