package ru.project.fitstyle.exception.newspage;

public enum ENewsPageError {
    OVER(2, "Page not found!");

    private final int code;
    private final String message;

    ENewsPageError(int code, String message)
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
