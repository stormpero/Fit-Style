package ru.project.fitstyle.service;

import ru.project.fitstyle.exception.AuthException;

public class AuthInfoService {
    private static AuthInfoService instance;
    private final Long id;
    private final String email;
    private final String token;

    private AuthInfoService(Long id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }

    public static void auth (Long id, String email, String token) {
        if (instance == null) {
            instance = new AuthInfoService(id, email, token);
        } else {
            throw new AuthException("User is already authenticated!");
        }
    }

    public static AuthInfoService getInstance() throws AuthException {
        if(instance != null) {
            return instance;
        } else {
            throw new AuthException("User is not authenticated!");
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public static void setInstance(AuthInfoService instance) {
        AuthInfoService.instance = instance;
    }
}
