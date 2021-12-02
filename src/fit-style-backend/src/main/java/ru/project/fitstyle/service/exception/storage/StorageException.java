package ru.project.fitstyle.service.exception.storage;

public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(String.format("Failed. %s", message));
    }
}
