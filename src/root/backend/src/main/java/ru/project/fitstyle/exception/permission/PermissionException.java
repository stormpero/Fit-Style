package ru.project.fitstyle.exception.permission;

public class PermissionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public PermissionException(EPermissionError permissionError) {
        super(String.format("Failed. %s", permissionError.getMessage()));
        this.errorCode = permissionError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
