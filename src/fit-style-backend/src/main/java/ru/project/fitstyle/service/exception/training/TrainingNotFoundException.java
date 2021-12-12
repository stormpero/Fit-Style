package ru.project.fitstyle.service.exception.training;

public class TrainingNotFoundException extends RuntimeException {
    public TrainingNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
