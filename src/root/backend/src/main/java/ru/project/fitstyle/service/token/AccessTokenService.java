package ru.project.fitstyle.service.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.security.jwt.JwtTokenHandler;
import ru.project.fitstyle.service.user.UserDetailsImpl;

import java.util.Date;

@Service
public class AccessTokenService {
    @Value("${authentication.auth.accessTokenSecret}")
    private String accessTokenSecret;

    @Value("${authentication.auth.accessTokenExpirationMs}")
    private Long accessTokenExpirationMs;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return generateTokenFromUsername(userPrincipal.getUsername());
    }

    public String generateTokenFromUsername(String username) {
        return JwtTokenHandler
                .generateJwtToken(username,
                new Date((new Date()).getTime() + accessTokenExpirationMs),
                accessTokenSecret);
    }

    public String getUsernameFromToken(String token) {
        return JwtTokenHandler
                .getUserNameFromJwtToken(token, accessTokenSecret);
    }

    public boolean validateToken(String token) {
        return JwtTokenHandler
                .validateToken(token, accessTokenSecret);
    }
}