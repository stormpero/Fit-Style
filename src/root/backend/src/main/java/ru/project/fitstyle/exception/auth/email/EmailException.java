package ru.project.fitstyle.exception.auth.email;

public class EmailException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public EmailException(EEmailError emailError) {
        super(String.format("Failed. %s", emailError.getMessage()));
        this.errorCode = emailError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
