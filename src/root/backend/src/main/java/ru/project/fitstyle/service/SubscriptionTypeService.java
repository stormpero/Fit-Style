package ru.project.fitstyle.service;

import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionType> getAllSubscriptionTypes();

    public Subscription createFitUserSubscription(Long subscriptionTypeId, String contractNumber);
}
