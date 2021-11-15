package ru.project.fitstyle.exception.auth.refresh;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenException extends RuntimeException {

    private static final long serialVersionUID = 131L;

    private final int errorCode;

    public RefreshTokenException(String token, ERefreshTokenError refreshTokenError) {
        super(String.format("Failed for [%s]: %s", token, refreshTokenError.getMessage()));
        this.errorCode = refreshTokenError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}