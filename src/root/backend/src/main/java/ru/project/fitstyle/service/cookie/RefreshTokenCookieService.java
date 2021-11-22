package ru.project.fitstyle.service.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.cookie.RefreshTokenCookieProperties;

@Service
public class RefreshTokenCookieService implements CookieService {

    private final String cookieName;

    @Autowired
    public RefreshTokenCookieService(RefreshTokenCookieProperties refreshTokenCookieProperties) {
        this.cookieName = refreshTokenCookieProperties.getName();
    }

    @Override
    public HttpCookie getCookie(String token, Long duration) {
        return ResponseCookie.from(cookieName, token)
                .maxAge(duration/1000)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .build();
    }
}
