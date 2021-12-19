package ru.project.fitstyle.config;

public enum Url {
    SERVER("http://localhost:8080/api"),

    AUTH("/auth/sign-in"),

    ROLE_ADD("/permission/role"),
    ALL_ROLES("/permission/all-roles"),

    ROLE_ASSIGN("/users/assign-role/"),
    ALL_USERS("/users/all"),

    SUBSCRIPTION_TYPE_ADD("/subscription-type"),
    ALL_SUBSCRIPTION_TYPES("/subscription-type"),

    TRAINING_TYPE_ADD("/training"),
    ALL_TRAINING_TYPES("/training");

    private final String url;

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
