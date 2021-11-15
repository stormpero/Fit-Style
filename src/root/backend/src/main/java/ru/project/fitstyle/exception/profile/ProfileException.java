package ru.project.fitstyle.exception.profile;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfileException extends RuntimeException{
    private static final long serialVersionUID = 4L;

    private final int profileErrorCode;

    public ProfileException(EProfileError profileErrorCode) {
        super(String.format("Failed. %s", profileErrorCode.getMessage()));
        this.profileErrorCode = profileErrorCode.getCode();
    }

    public int getProfileErrorCode() {
        return profileErrorCode;
    }
}
