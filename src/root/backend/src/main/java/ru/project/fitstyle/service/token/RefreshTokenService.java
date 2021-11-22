package ru.project.fitstyle.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.token.RefreshTokenProperties;
import ru.project.fitstyle.model.user.RefreshToken;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.repository.RefreshTokenRepository;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.security.jwt.JwtTokenHandler;

import java.time.Instant;
import java.util.Date;

@Service
public class RefreshTokenService implements TokenService{
    public String secret;

    public Long expirationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository,
                               RefreshTokenProperties refreshTokenProperties) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.secret = refreshTokenProperties.getSecret();
        this.expirationMs = refreshTokenProperties.getExpirationMs();
    }

    @Override
    public String generateTokenFromUser(FitUser user) {
        refreshTokenRepository.deleteByFitUser(user);
        Date validity = new Date(new Date().getTime() + expirationMs);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(validity.toInstant());
        refreshToken.setToken(
                JwtTokenHandler.generateJwtToken(user.getEmail(), validity, secret)
        );

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
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
    public boolean validate(Object token) {
        if (((RefreshToken)token).getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(((RefreshToken)token));
            return false;
        }

        return true;
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
