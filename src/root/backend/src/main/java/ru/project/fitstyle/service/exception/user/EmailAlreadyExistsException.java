package ru.project.fitstyle.service.exception.user;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(String.format("Failed. %s", message));
    }
}
