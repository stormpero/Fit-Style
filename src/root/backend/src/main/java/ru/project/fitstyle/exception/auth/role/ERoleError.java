package ru.project.fitstyle.exception.auth.role;

public enum ERoleError {
    NOT_FOUND(1, "Role is not found!");

    private final int code;
    private final String message;

    ERoleError(int code, String message)
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
