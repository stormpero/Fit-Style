package ru.project.fitstyle.exception.news;

public enum ENewsError {
    PAGE_NUMBER_LESS_THAN_ZERO(1, "Page number cannot be less than zero!"),
    OUT_OF_NEWS_PAGES(2, "No more news pages!"),
    MISSED_NEWS_WITH_ID(3, "News with that id has been deleted or never been created!");

    private final int code;
    private final String message;

    ENewsError(int code, String message)
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
