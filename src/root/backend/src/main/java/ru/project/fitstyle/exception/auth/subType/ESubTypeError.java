package ru.project.fitstyle.exception.auth.subType;

public enum ESubTypeError {
    NOT_FOUND(1, "SubscriptionType not found!");

    private final int code;
    private final String message;

    ESubTypeError(int code, String message)
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
