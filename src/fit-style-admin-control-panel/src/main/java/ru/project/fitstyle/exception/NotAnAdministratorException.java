package ru.project.fitstyle.exception;

public class NotAnAdministratorException extends RuntimeException {
    public NotAnAdministratorException(String message) {
        super(String.format("Failed. %s", message));
    }
}
