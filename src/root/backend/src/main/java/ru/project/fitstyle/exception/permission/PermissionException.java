package ru.project.fitstyle.exception.permission;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PermissionException extends RuntimeException {
    private static final long serialVersionUID = 31L;

    private final int errorCode;

    public PermissionException(EPermissionError permissionError) {
        super(String.format("Failed. %s", permissionError.getMessage()));
        this.errorCode = permissionError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
