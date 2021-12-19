package ru.project.fitstyle.service.exception.role;

public class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException(String message) {
        super(String.format("Failed. %s", message));
    }
}
