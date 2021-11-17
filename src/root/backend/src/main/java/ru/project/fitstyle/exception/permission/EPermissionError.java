package ru.project.fitstyle.exception.permission;

public enum EPermissionError {
    NOT_FOUND(1, "User not found!");

    private final int code;
    private final String message;

    EPermissionError(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
