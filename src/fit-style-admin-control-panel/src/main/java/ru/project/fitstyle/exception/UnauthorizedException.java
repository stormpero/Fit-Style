package ru.project.fitstyle.exception;

import java.io.IOException;

public class UnauthorizedException extends IOException {
    public UnauthorizedException(String message) {
        super(String.format("Failed. %s", message));
    }
}
