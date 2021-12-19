package ru.project.fitstyle.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.project.fitstyle.service.exception.recovery.RecoveryCodeExpiredException;
import ru.project.fitstyle.service.exception.recovery.UnableToSendEmailException;
import ru.project.fitstyle.service.exception.news.NewsPageNotFoundException;
import ru.project.fitstyle.service.exception.news.NewsStoryNotFoundException;
import ru.project.fitstyle.service.exception.recovery.WrongCodeException;
import ru.project.fitstyle.service.exception.role.UsersWithRoleNotFoundException;
import ru.project.fitstyle.service.exception.storage.FileNotFoundException;
import ru.project.fitstyle.service.exception.storage.StorageException;
import ru.project.fitstyle.service.exception.subscription.SubscriptionTypeNotFoundException;
import ru.project.fitstyle.service.exception.token.AnonymousTokenException;
import ru.project.fitstyle.service.exception.token.RefreshTokenNotValidException;
import ru.project.fitstyle.service.exception.training.ExceededMaxPeopleTrainingException;
import ru.project.fitstyle.service.exception.training.TrainingNotFoundException;
import ru.project.fitstyle.service.exception.training.UserAlreadySignedForTraining;
import ru.project.fitstyle.service.exception.user.BalanceLessThanZeroException;
import ru.project.fitstyle.service.exception.user.EmailAlreadyExistsException;
import ru.project.fitstyle.service.exception.user.NotACoachException;
import ru.project.fitstyle.service.exception.role.RoleNotFoundException;
import ru.project.fitstyle.service.exception.user.UserNotFoundException;

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
            FileNotFoundException.class, SubscriptionTypeNotFoundException.class, UserNotFoundException.class,
            RoleNotFoundException.class, TrainingNotFoundException.class, UsersWithRoleNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleNotFoundException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.NOT_FOUND.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {RefreshTokenNotValidException.class, RecoveryCodeExpiredException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleNotValid(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.NOT_VALID.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value={AnonymousTokenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleAnonymousTokenException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.ANONYMOUS_TOKEN.value(),
                request.getDescription(false));
    }


    @ExceptionHandler(value={UserAlreadySignedForTraining.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleUserAlreadySigned(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.ALREADY_SIGNED.value(),
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

    @ExceptionHandler(value = {ExceededMaxPeopleTrainingException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleExceededMaxPeopleTrainingException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.MAX_PEOPLE.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {UnableToSendEmailException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleUnableToSendEmailException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.UNABLE_TO_SEND_EMAIL.value(),
                request.getDescription(false));
    }

    @ExceptionHandler(value = {WrongCodeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleWrongCodeException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.WRONG_CODE.value(),
                request.getDescription(false));
    }

    public ErrorMessage handleAlreadyExistsException(RuntimeException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                EErrorCode.WRONG_CODE.value(),
                request.getDescription(false));
    }
}
