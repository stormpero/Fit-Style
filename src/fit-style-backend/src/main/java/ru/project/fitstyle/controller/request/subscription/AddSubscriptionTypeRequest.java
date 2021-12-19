package ru.project.fitstyle.controller.request.subscription;

import ru.project.fitstyle.model.entity.subscription.ESubsPlacementTime;
import ru.project.fitstyle.model.entity.subscription.Subscription;

import java.util.List;

public class AddSubscriptionTypeRequest {

    private String name;

    private int validityMonths;

    private ESubsPlacementTime placementTime;

    private String cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValidityMonths() {
        return validityMonths;
    }

    public void setValidityMonths(int validityMonths) {
        this.validityMonths = validityMonths;
    }

    public ESubsPlacementTime getPlacementTime() {
        return placementTime;
    }

    public void setPlacementTime(ESubsPlacementTime placementTime) {
        this.placementTime = placementTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
