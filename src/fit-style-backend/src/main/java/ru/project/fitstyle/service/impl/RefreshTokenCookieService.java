package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.RefreshTokenCookieProperties;
import ru.project.fitstyle.service.CookieService;

@Service
public class RefreshTokenCookieService implements CookieService {

    private final String cookieName;
    private final Long cookieExpireTime;

    @Autowired
    public RefreshTokenCookieService(RefreshTokenCookieProperties refreshTokenCookieProperties) {
        this.cookieName = refreshTokenCookieProperties.getName();
        this.cookieExpireTime = refreshTokenCookieProperties.getExpireTime();
    }

    @Override
    public HttpCookie createCookie(String token, Long duration) {
        return ResponseCookie.from(cookieName, token)
                .maxAge(cookieExpireTime)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();
    }
}
