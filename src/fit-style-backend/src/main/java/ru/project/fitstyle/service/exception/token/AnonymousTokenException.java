package ru.project.fitstyle.service.exception.token;

public class AnonymousTokenException extends RuntimeException{
    public AnonymousTokenException(String message) {
        super(String.format("Failed. %s", message));
    }
}
