package ru.project.fitstyle.exception.profile;

public enum EProfileError {
    NOT_FOUND(1, "User not found!");

    private final int code;
    private final String message;

    EProfileError(int code, String message)
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
