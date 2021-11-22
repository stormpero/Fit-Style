package ru.project.fitstyle.exception.storage;

public enum EStorageError {
    INIT(1, "Could not initialize storage location!"),
    READ(2, "Could not read stored files!"),
    STORE(3, "Could not store file!"),
    NOT_FOUND(4, "Could not find file!");

    private final int code;
    private final String message;

    EStorageError(int code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
