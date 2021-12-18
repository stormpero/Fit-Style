package ru.project.fitstyle.exception;

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(String.format("Failed. %s", message));
    }
}
