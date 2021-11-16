package ru.project.fitstyle.exception.news.story;

public enum ENewsStoryError {
    NOT_FOUND(3, "News with that id has been deleted or never been created!");

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
