package ru.project.fitstyle.advice;

public enum EErrorCode {
    NOT_FOUND(1),
    NOT_VALID(2),
    CANNOT_STORE(3),
    EXISTS(4),
    USER_IS_NOT_A_COACH(5),
    LESS_THAN_ZERO(6),
    ANONYMOUS_TOKEN(7),
    MAX_PEOPLE(8),
    ALREADY_SIGNED(9),
    UNABLE_TO_SEND_EMAIL(10),
    WRONG_CODE(11);

    private final int code;

    EErrorCode(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }
}