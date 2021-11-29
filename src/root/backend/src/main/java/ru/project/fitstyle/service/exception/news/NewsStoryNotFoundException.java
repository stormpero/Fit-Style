package ru.project.fitstyle.service.exception.news;

public class NewsStoryNotFoundException extends RuntimeException {
    public NewsStoryNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
