package ru.project.fitstyle.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import ru.project.fitstyle.advice.message.ErrorMessage;
import ru.project.fitstyle.exception.profile.ProfileException;

import java.util.Date;

public class ProfileControllerAdvice {
    @ExceptionHandler(value = ProfileException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleProfileException(ProfileException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getErrorCode(),
                request.getDescription(false));
    }
}
