package ru.project.fitstyle.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.project.fitstyle.payload.responsemessage.ErrorMessage;
import ru.project.fitstyle.exception.profile.ProfileException;

import java.util.Date;

@RestControllerAdvice
public class ProfileControllerAdvice {

    @ExceptionHandler(value = {ProfileException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleProfileException(ProfileException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                ex.getErrorCode(),
                request.getDescription(false));
    }
}
