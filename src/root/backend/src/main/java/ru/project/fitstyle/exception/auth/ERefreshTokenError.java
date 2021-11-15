package ru.project.fitstyle.exception.auth;

public enum ERefreshTokenError {
    MISSED(1, "Refresh token is not in database!"),
    EXPIRED(2, "Refresh token was expired. Please make a new signin request");

    private final int code;
    private final String message;

    ERefreshTokenError(int code, String message)
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
