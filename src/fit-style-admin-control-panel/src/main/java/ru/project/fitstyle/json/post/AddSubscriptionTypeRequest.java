package ru.project.fitstyle.json.post;

public class AddSubscriptionTypeRequest {

    private String name;

    private int validityMonths;

    private String placementTime;

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

    public String getPlacementTime() {
        return placementTime;
    }

    public void setPlacementTime(String placementTime) {
        this.placementTime = placementTime;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
