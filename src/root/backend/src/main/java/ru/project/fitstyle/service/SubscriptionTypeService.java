package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.subscription.Subscription;
import ru.project.fitstyle.model.dto.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionType> getAllSubscriptionTypes();

    public Subscription createFitUserSubscription(Long subscriptionTypeId, String contractNumber);
}
