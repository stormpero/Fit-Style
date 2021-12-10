package ru.project.fitstyle.service.exception.email;

public class UnableToSendEmailException extends RuntimeException {
    public UnableToSendEmailException(String message) {
        super(String.format("Failed. %s", message));
    }
}
