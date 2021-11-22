package ru.project.fitstyle.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.project.fitstyle.payload.responsemessage.ErrorMessage;
import ru.project.fitstyle.exception.news.page.NewsPageException;
import ru.project.fitstyle.exception.news.story.NewsStoryException;

import java.util.Date;

@RestControllerAdvice
public class NewsControllerAdvice {

    @ExceptionHandler({NewsPageException.class, NewsStoryException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage handleNewsPageException(NewsPageException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getErrorCode(),
                request.getDescription(false));
    }
}
