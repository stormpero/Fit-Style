package ru.project.fitstyle.payload.response.profile;

import ru.project.fitstyle.model.subscription.Subscription;

import java.sql.Date;

public class SubscriptionResponse {
    private Date beginDate;

    private Date endDate;

    private String contract;

    public SubscriptionResponse() {
    }

    public SubscriptionResponse(Date beginDate, Date endDate, String contract) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.contract = contract;
    }

    public SubscriptionResponse(Subscription subscription) {
        this.beginDate = subscription.getBeginDate();
        this.endDate = subscription.getEndDate();
        this.contract = subscription.getContractNumber();
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getContract() {
        return contract;
    }
}
