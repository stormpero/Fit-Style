package ru.project.fitstyle.service.exception.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
