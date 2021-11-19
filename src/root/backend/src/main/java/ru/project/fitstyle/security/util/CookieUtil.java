package ru.project.fitstyle.security.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;


@Component
public class CookieUtil {
    @Value("${authentication.token.authRefreshTokenCookieName}")
    private String refreshTokenCookieName;

    public HttpCookie createRefreshTokenCookie(String token, Long duration) {
        return ResponseCookie.from(refreshTokenCookieName, token)
                .maxAge(duration/1000)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();
    }
}
