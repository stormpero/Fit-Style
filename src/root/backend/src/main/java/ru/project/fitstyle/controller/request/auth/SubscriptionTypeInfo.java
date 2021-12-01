package ru.project.fitstyle.controller.request.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SubscriptionTypeInfo {
    @NotNull(message = "subscriptionTypeId should not be blank")
    private Long subscriptionTypeId;

    @NotBlank(message = "contractNumber should not be blank")
    @Size(min = 1, max = 15, message = "contractNumber should be more or equal than 1 and less or equal than 15 characters")
    private String contractNumber;

    public Long getSubscriptionTypeId() {
        return subscriptionTypeId;
    }

    public void setSubscriptionTypeId(Long subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
}
