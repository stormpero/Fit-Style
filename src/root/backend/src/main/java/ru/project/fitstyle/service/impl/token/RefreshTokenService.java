package ru.project.fitstyle.service.impl.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.RefreshTokenProperties;
import ru.project.fitstyle.model.entity.user.RefreshToken;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.dao.RefreshTokenRepository;
import ru.project.fitstyle.model.dao.FitUserRepository;
import ru.project.fitstyle.security.JwtTokenHandler;
import ru.project.fitstyle.service.TokenService;
import ru.project.fitstyle.service.exception.token.RefreshTokenExpiredException;
import ru.project.fitstyle.service.exception.token.RefreshTokenNotFoundException;
import ru.project.fitstyle.service.exception.user.UserNotFoundException;

import java.time.Instant;
import java.util.Date;

@Service
public class RefreshTokenService implements TokenService {
    public String secret;

    public Long expirationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final FitUserRepository fitUserRepository;

    @Autowired
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, FitUserRepository fitUserRepository,
                               RefreshTokenProperties refreshTokenProperties) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.fitUserRepository = fitUserRepository;
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
        FitUser user = fitUserRepository.findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User with that email cannot be found!"));
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
                        new RefreshTokenNotFoundException("Refresh token not found!"));

        if (refreshToken.getExpiryDate().compareTo(Instant.now()) >= 0) {
            return refreshToken;
        }
        else {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenExpiredException("Refresh token expired!");
        }
    }

    @Override
    public Long getExpirationMs() {
        return expirationMs;
    }

}
