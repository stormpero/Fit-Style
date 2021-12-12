package ru.project.fitstyle.service.exception.recovery;

public class RecoveryCodeExpiredException extends RuntimeException {
    public RecoveryCodeExpiredException(String message) {
        super(String.format("Failed. %s", message));
    }
}
