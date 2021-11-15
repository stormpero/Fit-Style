package ru.project.fitstyle.exception.auth.email;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailException extends RuntimeException{
    private static final long serialVersionUID = 11L;

    private final int errorCode;

    public EmailException(EEmailError emailError) {
        super(String.format("Failed. %s", emailError.getMessage()));
        this.errorCode = emailError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
