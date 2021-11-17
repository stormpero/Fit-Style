package ru.project.fitstyle.services.token;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.services.token.jwt.JwtTokenHandler;
import ru.project.fitstyle.services.user.UserDetailsImpl;

import java.util.Date;

@Service
public class AccessTokenService {
    @Value("${authentication.auth.accessTokenSecret}")
    private String accessTokenSecret;

    @Value("${authentication.auth.accessTokenExpirationMs}")
    private Long accessTokenExpirationMs;

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        long now = new Date().getTime();
        Date validity = new Date(now + accessTokenExpirationMs);

        return JwtTokenHandler.generateJwtToken(userPrincipal.getUsername(), validity, accessTokenSecret);
    }

    public String getUsernameFromToken(String token) {
        return JwtTokenHandler.getUserNameFromJwtToken(token, accessTokenSecret);
    }

    public String generateTokenFromUsername(String username) {
        return JwtTokenHandler.
                generateTokenFromUsername(username, accessTokenExpirationMs, accessTokenSecret);
    }

    public String getAccessTokenSecret() {
        return accessTokenSecret;
    }

    public Long getAccessTokenExpirationMs() {
        return accessTokenExpirationMs;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(JwtTokenHandler.getSigningKey(accessTokenSecret))
                    .build().parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}