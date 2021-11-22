package ru.project.fitstyle.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.project.fitstyle.exception.subscriptionType.SubscriptionTypeException;
import ru.project.fitstyle.payload.message.ErrorMessage;

import java.util.Date;

@RestControllerAdvice
public class SubscriptionTypeAdvice {

    @ExceptionHandler(value = SubscriptionTypeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleProfileException(SubscriptionTypeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                ex.getErrorCode(),
                request.getDescription(false));
    }
}

