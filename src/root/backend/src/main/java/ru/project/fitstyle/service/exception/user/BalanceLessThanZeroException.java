package ru.project.fitstyle.service.exception.user;

public class BalanceLessThanZeroException extends RuntimeException{
    public BalanceLessThanZeroException(String message) {
        super(String.format("Failed. %s", message));
    }
}
