package ru.project.fitstyle.json.response;

import java.util.List;

public class AllSubscriptionTypeResponse {
    private List<SubscriptionTypeDto> subscriptionTypes;

    public AllSubscriptionTypeResponse(List<SubscriptionTypeDto> subscriptionTypes) {
        this.subscriptionTypes = subscriptionTypes;
    }

    public AllSubscriptionTypeResponse() {
    }

    public List<SubscriptionTypeDto> getSubscriptionTypes() {
        return subscriptionTypes;
    }


    public static class SubscriptionTypeDto {
        private Long id;

        private String name;

        private int validityMonths;

        private String placementTime;

        private String cost;

        public SubscriptionTypeDto(final Long id, final String name, final int validityMonths, final String placementTime, final String cost) {
            this.id = id;
            this.name = name;
            this.validityMonths = validityMonths;
            this.placementTime = placementTime;
            this.cost = cost;
        }

        public SubscriptionTypeDto() {
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

        public String getPlacementTime() {
            return placementTime;
        }

        public String getCost() {
            return cost;
        }
    }
}
