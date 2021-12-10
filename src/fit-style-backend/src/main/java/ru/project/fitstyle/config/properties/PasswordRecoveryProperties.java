package ru.project.fitstyle.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "password-recovery")
public class PasswordRecoveryProperties {
    /**
     * Time in ms after user won't be able to recover his password
     */
    private Long deadlineMs;

    public Long getDeadlineMs() {
        return deadlineMs;
    }

    public void setDeadlineMs(Long deadlineMs) {
        this.deadlineMs = deadlineMs;
    }
}
