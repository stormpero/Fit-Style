package ru.project.fitstyle.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.token.AccessTokenProperties;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.security.jwt.JwtTokenHandler;

import java.util.Date;

@Service
public class AccessTokenService implements TokenService{

    private final String secret;

    private final Long expirationMs;

    private final UserRepository userRepository;

    @Autowired
    public AccessTokenService(UserRepository userRepository, AccessTokenProperties accessTokenProperties) {
        this.userRepository = userRepository;
        this.secret = accessTokenProperties.getSecret();
        this.expirationMs = accessTokenProperties.getExpirationMs();
    }

    @Override
    public String generateTokenFromUser(FitUser user) {
        return JwtTokenHandler
                .generateJwtToken(user.getEmail(),
                new Date((new Date()).getTime() + expirationMs),
                secret);
    }

    @Override
    public String generateTokenFromUsername(String username) {
        FitUser user = userRepository.findByEmail(username).get();
        return generateTokenFromUser(user);
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
    public String getSecret() {
        return secret;
    }

    @Override
    public Long getExpirationMs() {
        return expirationMs;
    }
}