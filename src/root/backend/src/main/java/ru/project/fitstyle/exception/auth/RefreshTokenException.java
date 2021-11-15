package ru.project.fitstyle.exception.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final int refreshTokenErrorCode;

    public RefreshTokenException(String token, ERefreshTokenError refreshTokenError) {
        super(String.format("Failed for [%s]: %s", token, refreshTokenError.getMessage()));
        this.refreshTokenErrorCode = refreshTokenError.getCode();
    }

    public int getRefreshTokenErrorCode() {
        return refreshTokenErrorCode;
    }
}