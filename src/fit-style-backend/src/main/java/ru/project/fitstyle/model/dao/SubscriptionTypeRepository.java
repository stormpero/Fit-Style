package ru.project.fitstyle.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.project.fitstyle.model.dto.subscription.SubscriptionTypeInfo;
import ru.project.fitstyle.model.entity.subscription.SubscriptionType;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, Long> {
    @Query(value = "select new ru.project.fitstyle.model.dto.subscription.SubscriptionTypeInfo(v.id, v.name, v.validityMonths, v.placementTime, v.cost) " +
            "from SubscriptionType v")
    Optional<List<SubscriptionTypeInfo>> findAllSubscriptions();
}
