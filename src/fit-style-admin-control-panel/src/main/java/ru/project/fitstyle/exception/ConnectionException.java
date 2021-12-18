package ru.project.fitstyle.exception;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(String.format("Failed. %s", message));
    }
}
