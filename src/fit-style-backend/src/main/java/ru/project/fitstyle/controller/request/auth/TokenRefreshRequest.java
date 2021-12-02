package ru.project.fitstyle.controller.request.auth;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {
    @NotBlank(message = "refreshToken should not be blank")
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}