package ru.project.fitstyle.service.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.fitstyle.exception.auth.refresh.ERefreshTokenError;
import ru.project.fitstyle.exception.auth.refresh.RefreshTokenException;
import ru.project.fitstyle.model.user.RefreshToken;
import ru.project.fitstyle.model.user.User;
import ru.project.fitstyle.repository.RefreshTokenRepository;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.security.jwt.JwtTokenHandler;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenService {
    @Value("${authentication.token.refreshTokenSecret}")
    public String refreshTokenSecret;

    @Value("${authentication.token.refreshTokenExpirationMs}")
    public Long refreshTokenExpirationMs;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public RefreshTokenService(UserRepository userRepository,
                               RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String generateToken(Long userId) {
        User user = userRepository.findById(userId).get();
        return generateTokenFromUser(user);
    }

    public String generateTokenFromUser(User user) {
        deleteByUser(user);
        Date validity = new Date(new Date().getTime() + refreshTokenExpirationMs);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(validity.toInstant());
        refreshToken.setToken(
                JwtTokenHandler.generateJwtToken(user.getEmail(), validity, refreshTokenSecret)
        );

        refreshToken = refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenException(token.getToken(),
                    ERefreshTokenError.EXPIRED);
        }

        return token;
    }



    @Transactional
    public void deleteByUserId(Long userId) {
        deleteByUser(userRepository.findById(userId).get());
    }
    public void deleteByUsername(String username) {
        deleteByUser(userRepository.findByEmail(username).get());
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

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }



    public String getRefreshTokenSecret() {
        return refreshTokenSecret;
    }

    public Long getRefreshTokenExpirationMs() {
        return refreshTokenExpirationMs;
    }

}
