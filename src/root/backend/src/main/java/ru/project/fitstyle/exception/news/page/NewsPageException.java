package ru.project.fitstyle.exception.news.page;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NewsPageException extends RuntimeException{
    private static final long serialVersionUID = 21L;

    private final int errorCode;

    public NewsPageException(ENewsPageError newsError) {
        super(String.format("Failed. %s", newsError.getMessage()));
        this.errorCode = newsError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
