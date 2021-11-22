package ru.project.fitstyle.exception.email;

public enum EEmailError {
    OCCUPIED(1, "Email is already in use!");

    private final int code;
    private final String message;

    EEmailError(int code, String message)
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
