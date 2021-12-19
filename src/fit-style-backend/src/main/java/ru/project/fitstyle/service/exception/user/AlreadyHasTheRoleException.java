package ru.project.fitstyle.service.exception.user;

public class AlreadyHasTheRoleException extends RuntimeException {
    public AlreadyHasTheRoleException(String message) {
        super(String.format("Failed. %s", message));
    }
}
