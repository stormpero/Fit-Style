package ru.project.fitstyle.service.exception.training;

public class ExceededMaxPeopleTrainingException extends RuntimeException {
    public ExceededMaxPeopleTrainingException(String message) {
        super(String.format("Failed. %s", message));
    }
}
