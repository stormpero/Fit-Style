package ru.project.fitstyle.config;

public enum Url {
    SERVER("http://localhost:8080/api"),

    AUTH("/auth/sign-in");

    private final String url;

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
