package ru.project.fitstyle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.subscription.SubscriptionType;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, Long> {
}
