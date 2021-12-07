package ru.project.fitstyle.service.exception.training;

public class UserAlreadySignedForTraining extends RuntimeException {
    public UserAlreadySignedForTraining(String message) {
        super(String.format("Failed. %s", message));
    }
}
