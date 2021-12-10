package ru.project.fitstyle.service.exception.recovery;

public class WrongCodeException extends RuntimeException{
    public WrongCodeException(String message) {
        super(String.format("Failed. %s", message));
    }
}
