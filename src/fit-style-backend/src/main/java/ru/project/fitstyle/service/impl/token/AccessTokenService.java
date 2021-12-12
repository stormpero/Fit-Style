package ru.project.fitstyle.service.impl.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.AccessTokenProperties;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.security.JwtTokenHandler;
import ru.project.fitstyle.service.TokenService;

import java.util.Date;

@Service
public class AccessTokenService implements TokenService {

    private final String secret;

    private final Long expirationMs;

    @Autowired
    public AccessTokenService(AccessTokenProperties accessTokenProperties) {
        this.secret = accessTokenProperties.getSecret();
        this.expirationMs = accessTokenProperties.getExpirationMs();
    }

    @Override
    public String generateTokenFromUser(FitUser user) {
        return generateTokenFromUsername(user.getEmail());
    }

    @Override
    public String generateTokenFromUsername(String username) {
        return JwtTokenHandler
                .generateJwtToken(username,
                        new Date((new Date()).getTime() + expirationMs),
                        secret);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return JwtTokenHandler
                .getUserNameFromJwtToken(token, secret);
    }

    @Override
    public Object validate(String token) {
        return JwtTokenHandler
                .validateToken(token, secret);
    }

    @Override
    public Long getExpirationMs() {
        return expirationMs;
    }
}