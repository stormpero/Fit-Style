package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.subscription.SubscriptionTypeDto;
import ru.project.fitstyle.model.entity.subscription.Subscription;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionTypeDto> getAllSubscriptionTypes();

    Subscription createFitUserSubscription(Long subscriptionTypeId, String contractNumber);
}
