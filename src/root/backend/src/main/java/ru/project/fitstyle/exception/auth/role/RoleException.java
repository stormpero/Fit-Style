package ru.project.fitstyle.exception.auth.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoleException extends RuntimeException{
    private static final long serialVersionUID = 12L;

    private final int errorCode;

    public RoleException(ERoleError roleError) {
        super(String.format("Failed. %s", roleError.getMessage()));
        this.errorCode = roleError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
