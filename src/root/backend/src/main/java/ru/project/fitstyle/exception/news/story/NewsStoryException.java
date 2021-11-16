package ru.project.fitstyle.exception.news.story;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NewsStoryException extends RuntimeException{
    private static final long serialVersionUID = 22L;

    private final int errorCode;

    public NewsStoryException(ENewsStoryError newsStoryError) {
        super(String.format("Failed. %s", newsStoryError.getMessage()));
        this.errorCode = newsStoryError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
