package ru.project.fitstyle.service.exception.news;

public class NewsPageNotFoundException extends RuntimeException {
    public NewsPageNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
