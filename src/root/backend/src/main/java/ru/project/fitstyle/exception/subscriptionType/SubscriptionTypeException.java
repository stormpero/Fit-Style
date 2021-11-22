package ru.project.fitstyle.exception.subscriptionType;

public class SubscriptionTypeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public SubscriptionTypeException(ESubscriptionTypeError subscriptionTypeError) {
        super(String.format("Failed. %s", subscriptionTypeError.getMessage()));
        this.errorCode = subscriptionTypeError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
