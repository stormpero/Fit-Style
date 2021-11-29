package ru.project.fitstyle.service.exception.storage;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
