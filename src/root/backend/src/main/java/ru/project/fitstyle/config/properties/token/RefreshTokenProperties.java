package ru.project.fitstyle.config.properties.token;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "refreshtoken")
public class RefreshTokenProperties {
    /**
     * Private key that is used to encode refresh token
     */
    private String secret;
    /**
     * Time in ms after refresh token will become invalid
     */
    private Long expirationMs;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpirationMs() {
        return expirationMs;
    }

    public void setExpirationMs(Long expirationMs) {
        this.expirationMs = expirationMs;
    }
}
