package ru.project.fitstyle.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "authentication")
public class AuthenticationProperties {
    Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public static class Token {
        /**
         * Private key that is used to encode access token
         */
        private String accessTokenSecret;
        /**
         * Private key that is used to encode refresh token
         */
        private String refreshTokenSecret;
        /**
         * Time in ms after access token will become invalid
         */
        private Long accessTokenExpirationMs;
        /**
         * Time in ms after refresh token will become invalid
         */
        private Long refreshTokenExpirationMs;
        /**
         * Refresh token cookie name
         */
        private String authRefreshTokenCookieName;

        public String getAccessTokenSecret() {
            return accessTokenSecret;
        }

        public void setAccessTokenSecret(String accessTokenSecret) {
            this.accessTokenSecret = accessTokenSecret;
        }

        public String getRefreshTokenSecret() {
            return refreshTokenSecret;
        }

        public void setRefreshTokenSecret(String refreshTokenSecret) {
            this.refreshTokenSecret = refreshTokenSecret;
        }

        public Long getAccessTokenExpirationMs() {
            return accessTokenExpirationMs;
        }

        public void setAccessTokenExpirationMs(Long accessTokenExpirationMs) {
            this.accessTokenExpirationMs = accessTokenExpirationMs;
        }

        public Long getRefreshTokenExpirationMs() {
            return refreshTokenExpirationMs;
        }

        public void setRefreshTokenExpirationMs(Long refreshTokenExpirationMs) {
            this.refreshTokenExpirationMs = refreshTokenExpirationMs;
        }

        public String getAuthRefreshTokenCookieName() {
            return authRefreshTokenCookieName;
        }

        public void setAuthRefreshTokenCookieName(String authRefreshTokenCookieName) {
            this.authRefreshTokenCookieName = authRefreshTokenCookieName;
        }
    }
}
