package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.dto.subscription.SubscriptionTypeInfo;
import ru.project.fitstyle.model.entity.subscription.Subscription;
import ru.project.fitstyle.model.entity.subscription.SubscriptionType;
import ru.project.fitstyle.model.dao.SubscriptionTypeRepository;
import ru.project.fitstyle.service.SubscriptionTypeService;
import ru.project.fitstyle.service.exception.subscription.SubscriptionTypeNotFoundException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class FitSubscriptionTypeService implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    @Autowired
    public FitSubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionTypeInfo> getAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAllSubscriptions()
                .filter(list -> list.size() != 0)
                .orElseThrow(() -> new SubscriptionTypeNotFoundException("There are no subscription types!"));
    }

    @Override
    public Subscription createFitUserSubscription(Long subscriptionTypeId, String contractNumber) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId)
                .orElseThrow(() -> new SubscriptionTypeNotFoundException("Subscription type with that id cannot be found"));

        Subscription subscription = new Subscription();
        Date beginDate = new Date(new Date().getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.add(Calendar.MONTH, subscriptionType.getValidityMonths());

        subscription.setBeginDate(beginDate);
        subscription.setEndDate(calendar.getTime());
        subscription.setSubscriptionType(subscriptionType);
        subscription.setContractNumber(contractNumber);

        return subscription;
    }
}
