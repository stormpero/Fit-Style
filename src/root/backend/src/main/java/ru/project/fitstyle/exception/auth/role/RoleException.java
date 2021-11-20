package ru.project.fitstyle.exception.auth.role;

public class RoleException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public RoleException(ERoleError roleError) {
        super(String.format("Failed. %s", roleError.getMessage()));
        this.errorCode = roleError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
