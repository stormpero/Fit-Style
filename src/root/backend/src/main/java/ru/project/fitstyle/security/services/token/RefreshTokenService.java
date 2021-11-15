package ru.project.fitstyle.security.services.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.fitstyle.exception.auth.refresh.ERefreshTokenError;
import ru.project.fitstyle.exception.auth.refresh.RefreshTokenException;
import ru.project.fitstyle.models.user.RefreshToken;
import ru.project.fitstyle.models.user.User;
import ru.project.fitstyle.repository.RefreshTokenRepository;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.security.services.token.jwt.JwtTokenHandler;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenService {
    @Value("${authentication.auth.refreshTokenSecret}")
    public String refreshTokenSecret;

    @Value("${authentication.auth.refreshTokenExpirationMs}")
    public Long refreshTokenExpirationMs;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(UserRepository userRepository,
                               RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public RefreshToken generateToken(Long userId) {
        User user = userRepository.findById(userId).get();
        return generateTokenFromUser(user);
    }

    public RefreshToken generateTokenFromUser(User user) {
        deleteByUser(user);
        long now = new Date().getTime();
        Date validity = new Date(now + refreshTokenExpirationMs);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(validity.toInstant());
        refreshToken.setToken(
                JwtTokenHandler.generateJwtToken(user.getEmail(), validity, refreshTokenSecret)
        );

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException(token.getToken(),
                    ERefreshTokenError.EXPIRED);
        }

        return token;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    @Transactional
    public void deleteByUserId(Long userId) {
        deleteByUser(userRepository.findById(userId).get());
    }
    @Transactional
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
    @Transactional
    public RefreshToken save(RefreshToken refreshToken)
    {
        return refreshTokenRepository.save(refreshToken);
    }

    public String getRefreshTokenSecret() {
        return refreshTokenSecret;
    }

    public Long getRefreshTokenExpirationMs() {
        return refreshTokenExpirationMs;
    }

}
