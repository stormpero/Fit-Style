package ru.project.fitstyle.exception.storage;

import ru.project.fitstyle.exception.subscriptionType.ESubscriptionTypeError;

public class StorageException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final int errorCode;

    public StorageException(EStorageError storageError) {
        super(String.format("Failed. %s", storageError.getMessage()));
        this.errorCode = storageError.getCode();
    }

    public int getErrorCode() {
        return errorCode;
    }
}
