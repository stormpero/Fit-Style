package ru.project.fitstyle.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.RefreshTokenProperties;
import ru.project.fitstyle.exception.refresh.ERefreshTokenError;
import ru.project.fitstyle.exception.refresh.RefreshTokenException;
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
    public Object validate(String token) {
        RefreshToken refreshToken= refreshTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new RefreshTokenException(token,
                                ERefreshTokenError.NOT_FOUND));

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) >= 0) {
            return refreshToken;
        }
        else {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenException(refreshToken.getToken(),
                    ERefreshTokenError.EXPIRED);
        }
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
