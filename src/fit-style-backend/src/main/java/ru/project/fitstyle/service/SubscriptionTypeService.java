package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.subscription.SubscriptionTypeDto;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionTypeDto> getAllSubscriptionTypes();

    Subscription createFitUserSubscription(final Long subscriptionTypeId, final String contractNumber);

    void save(SubscriptionType subscriptionType);
}
