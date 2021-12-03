package ru.project.fitstyle.service.exception.token;

public class RefreshTokenNotValidException extends RuntimeException{
    public RefreshTokenNotValidException(String message) {
        super(String.format("Failed. %s", message));
    }
}
