package ru.project.fitstyle.advice;

import java.util.Date;

public class ErrorMessage {
    private final int statusCode;
    private final Date timestamp;
    private final String message;
    private final int refreshTokenErrorCode;
    private final String description;

    public ErrorMessage(int statusCode, Date timestamp, String message,
                        int refreshTokenErrorCode, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.refreshTokenErrorCode = refreshTokenErrorCode;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public int getRefreshTokenErrorCode() {
        return refreshTokenErrorCode;
    }

    public String getDescription() {
        return description;
    }
}