package ru.project.fitstyle.controller.response.other;

public class SuccessMessage {
    private final String message;

    public SuccessMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
