package ru.project.fitstyle.service.exception.user;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
