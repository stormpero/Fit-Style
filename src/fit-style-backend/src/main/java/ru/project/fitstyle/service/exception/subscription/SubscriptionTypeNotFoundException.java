package ru.project.fitstyle.service.exception.subscription;

public class SubscriptionTypeNotFoundException extends RuntimeException {
    public SubscriptionTypeNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
