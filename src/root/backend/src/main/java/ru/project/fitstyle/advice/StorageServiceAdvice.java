package ru.project.fitstyle.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.project.fitstyle.exception.storage.StorageException;
import ru.project.fitstyle.payload.responsemessage.ErrorMessage;

import java.util.Date;

@RestControllerAdvice
public class StorageServiceAdvice {
    @ExceptionHandler(value = StorageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleProfileException(StorageException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getErrorCode(),
                request.getDescription(false));
    }
}
