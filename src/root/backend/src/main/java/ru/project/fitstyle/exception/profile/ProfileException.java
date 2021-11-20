package ru.project.fitstyle.exception.profile;

public class ProfileException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public ProfileException(EProfileError profileErrorCode) {
        super(String.format("Failed. %s", profileErrorCode.getMessage()));
        this.errorCode = profileErrorCode.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
