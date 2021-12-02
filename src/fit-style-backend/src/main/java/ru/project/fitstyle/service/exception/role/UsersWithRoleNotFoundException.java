package ru.project.fitstyle.service.exception.role;

public class UsersWithRoleNotFoundException extends RuntimeException {
    public UsersWithRoleNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
