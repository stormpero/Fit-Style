package ru.project.fitstyle.advice;

public enum ErrorCode {
    NOT_FOUND(1),
    REFRESH_TOKEN_EXPIRED(2),
    CANNOT_STORE(3),
    EMAIL_ALREADY_EXISTS(4), USER_IS_NOT_A_COACH(5), LESS_THAN_ZERO(6);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }
}