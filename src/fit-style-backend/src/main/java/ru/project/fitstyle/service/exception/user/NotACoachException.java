package ru.project.fitstyle.service.exception.user;

public class NotACoachException extends RuntimeException {
    public NotACoachException(String message) {
        super(String.format("Failed. %s", message));
    }
}
