package ru.project.fitstyle.service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.subscription.SubscriptionType;
import ru.project.fitstyle.repository.SubscriptionTypeRepository;

import java.util.List;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    @Autowired
    public SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @Override
    public List<SubscriptionType> getAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAll();
    }
}
