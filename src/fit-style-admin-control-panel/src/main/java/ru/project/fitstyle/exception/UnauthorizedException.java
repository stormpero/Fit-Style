package ru.project.fitstyle.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(String.format("Failed. %s", message));
    }
}
