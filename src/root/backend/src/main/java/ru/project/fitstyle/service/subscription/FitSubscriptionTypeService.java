package ru.project.fitstyle.service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.dto.subscription.SubscriptionType;
import ru.project.fitstyle.model.dao.SubscriptionTypeRepository;

import java.util.List;

@Service
public class FitSubscriptionTypeService implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    @Autowired
    public FitSubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionType> getAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAll();
    }
}
