package ru.project.fitstyle.service.exception.subscription;

public class SubscriptionAlreadyExistsException extends RuntimeException{
    public SubscriptionAlreadyExistsException(String message) {
        super(String.format("Failed. %s", message));
    }
}
