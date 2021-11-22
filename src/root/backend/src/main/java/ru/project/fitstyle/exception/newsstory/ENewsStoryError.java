package ru.project.fitstyle.exception.newsstory;

public enum ENewsStoryError {
    NOT_FOUND(1, "News not found!");

    private final int code;
    private final String message;

    ENewsStoryError(int code, String message)
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
