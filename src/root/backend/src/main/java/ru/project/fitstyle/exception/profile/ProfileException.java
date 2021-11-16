package ru.project.fitstyle.exception.profile;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfileException extends RuntimeException{
    private static final long serialVersionUID = 41L;

    private final int errorCode;

    public ProfileException(EProfileError profileErrorCode) {
        super(String.format("Failed. %s", profileErrorCode.getMessage()));
        this.errorCode = profileErrorCode.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
