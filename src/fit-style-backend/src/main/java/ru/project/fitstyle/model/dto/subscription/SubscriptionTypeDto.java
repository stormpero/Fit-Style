package ru.project.fitstyle.model.dto.subscription;

import ru.project.fitstyle.model.entity.subscription.ESubsPlacementTime;

public class SubscriptionTypeDto {
    private final Long id;

    private final String name;

    private final int validityMonths;

    private final ESubsPlacementTime placementTime;

    private final String cost;

    public SubscriptionTypeDto(Long id, String name, int validityMonths, ESubsPlacementTime placementTime, String cost) {
        this.id = id;
        this.name = name;
        this.validityMonths = validityMonths;
        this.placementTime = placementTime;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getValidityMonths() {
        return validityMonths;
    }

    public ESubsPlacementTime getPlacementTime() {
        return placementTime;
    }

    public String getCost() {
        return cost;
    }
}
