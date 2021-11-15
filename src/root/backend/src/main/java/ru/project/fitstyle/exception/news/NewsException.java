package ru.project.fitstyle.exception.news;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NewsException extends RuntimeException{
    private static final long serialVersionUID = 2L;

    private final int newsErrorCode;

    public NewsException(ENewsError newsError) {
        super(String.format("Failed. %s", newsError.getMessage()));
        this.newsErrorCode = newsError.getCode();
    }

    public int getNewsErrorCode() {
        return newsErrorCode;
    }
}
