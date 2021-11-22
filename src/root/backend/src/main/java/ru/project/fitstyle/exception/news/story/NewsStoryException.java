package ru.project.fitstyle.exception.news.story;

public class NewsStoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public NewsStoryException(ENewsStoryError newsStoryError) {
        super(String.format("Failed. %s", newsStoryError.getMessage()));
        this.errorCode = newsStoryError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
