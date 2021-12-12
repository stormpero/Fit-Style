package ru.project.fitstyle.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "training")
public class TrainingProperties {
    /**
     * Max users in one group training group*/
    private int maxUsersPerGroup;

    public int getMaxUsersPerGroup() {
        return maxUsersPerGroup;
    }

    public void setMaxUsersPerGroup(int maxUsersPerGroup) {
        this.maxUsersPerGroup = maxUsersPerGroup;
    }
}
