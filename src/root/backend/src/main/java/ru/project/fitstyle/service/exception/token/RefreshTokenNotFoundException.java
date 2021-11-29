package ru.project.fitstyle.service.exception.token;

public class RefreshTokenNotFoundException extends RuntimeException {
    public RefreshTokenNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
