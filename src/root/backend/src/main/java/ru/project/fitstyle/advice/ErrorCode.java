package ru.project.fitstyle.advice;

public enum ErrorCode {
    NOT_FOUND(1),
    REFRESH_TOKEN_EXPIRED(2),
    CANNOT_STORE(3),
    EMAIL_ALREADY_EXISTS(4);
    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }
}