package ru.project.fitstyle.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.project.fitstyle.advice.message.ErrorMessage;
import ru.project.fitstyle.exception.permission.PermissionException;

import java.util.Date;

@RestControllerAdvice
public class PermissionControllerAdvice {
    @ExceptionHandler(value = PermissionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlePermissionException(PermissionException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getErrorCode(),
                request.getDescription(false));
    }
}