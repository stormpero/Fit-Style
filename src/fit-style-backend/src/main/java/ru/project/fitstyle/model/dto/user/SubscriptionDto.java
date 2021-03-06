package ru.project.fitstyle.model.dto.user;

import java.util.Date;

public class SubscriptionDto {
    private final String name;

    private final Date endDate;

    public SubscriptionDto(final String name, final Date endDate) {
        this.name = name;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public Date getEndDate() {
        return endDate;
    }
}
