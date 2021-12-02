package ru.project.fitstyle.service.exception.token;

public class RefreshTokenExpiredException extends RuntimeException {
    public RefreshTokenExpiredException(String message) {
        super(String.format("Failed. %s", message));
    }
}
