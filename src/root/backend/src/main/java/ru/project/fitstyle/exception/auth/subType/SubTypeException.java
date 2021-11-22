package ru.project.fitstyle.exception.auth.subType;

public class SubTypeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public SubTypeException(ESubTypeError subscriptionTypeError) {
        super(String.format("Failed. %s", subscriptionTypeError.getMessage()));
        this.errorCode = subscriptionTypeError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
