package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.subscription.SubscriptionTypeInfo;
import ru.project.fitstyle.model.entity.subscription.Subscription;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionTypeInfo> getAllSubscriptionTypes();

    Subscription createFitUserSubscription(Long subscriptionTypeId, String contractNumber);
}
