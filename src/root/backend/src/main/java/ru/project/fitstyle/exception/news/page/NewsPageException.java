package ru.project.fitstyle.exception.news.page;

public class NewsPageException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public NewsPageException(ENewsPageError newsError) {
        super(String.format("Failed. %s", newsError.getMessage()));
        this.errorCode = newsError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
