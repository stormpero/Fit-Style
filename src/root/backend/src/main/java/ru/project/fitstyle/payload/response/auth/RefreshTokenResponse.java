package ru.project.fitstyle.payload.response.auth;

public class RefreshTokenResponse {
    private final String tokenType = "Bearer";
    private final String accessToken;

    public RefreshTokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }
}