package ru.project.fitstyle.exception.subscriptionType;

public enum ESubscriptionTypeError {
    NOTHING(1, "There are not SubscriptionTypes!");

    private final int code;
    private final String message;

    ESubscriptionTypeError(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
