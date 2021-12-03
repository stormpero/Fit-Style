package ru.project.fitstyle.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.project.fitstyle.service.exception.news.NewsPageNotFoundException;
import ru.project.fitstyle.service.exception.news.NewsStoryNotFoundException;
import ru.project.fitstyle.service.exception.role.UsersWithRoleNotFoundException;
import ru.project.fitstyle.service.exception.storage.StorageException;
import ru.project.fitstyle.service.exception.subscription.SubscriptionTypeNotFoundException;
import ru.project.fitstyle.service.exception.token.RefreshTokenExpiredException;
import ru.project.fitstyle.service.exception.token.RefreshTokenNotFoundException;
import ru.project.fitstyle.service.exception.training.TrainingNotFoundException;
import ru.project.fitstyle.service.exception.user.BalanceLessThanZeroException;
import ru.project.fitstyle.service.exception.user.EmailAlreadyExistsException;
import ru.project.fitstyle.service.exception.user.NotACoachException;
import ru.project.fitstyle.service.exception.role.RoleNotFoundException;
import ru.project.fitstyle.service.exception.user.UserNotFoundException;

import java.io.FileNotFoundException;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleEmailAlreadyExistsException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.EMAIL_ALREADY_EXISTS.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {NewsPageNotFoundException.class, NewsStoryNotFoundException.class,
            FileNotFoundException.class, SubscriptionTypeNotFoundException.class, RefreshTokenNotFoundException.class,
            UserNotFoundException.class, RoleNotFoundException.class, TrainingNotFoundException.class,
            UsersWithRoleNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.NOT_FOUND.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {RefreshTokenExpiredException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleRefreshTokenExpiredException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.REFRESH_TOKEN_EXPIRED.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {StorageException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleStorageException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.CANNOT_STORE.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {NotACoachException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleNotACoachException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.USER_IS_NOT_A_COACH.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {BalanceLessThanZeroException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleBalanceLessThanZeroException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.LESS_THAN_ZERO.value(),
                request.getDescription(false));
    }
}
