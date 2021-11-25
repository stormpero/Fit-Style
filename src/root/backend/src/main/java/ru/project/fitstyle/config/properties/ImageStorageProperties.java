package ru.project.fitstyle.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "image")
public class ImageStorageProperties {

    /**
     * Location where our file will be stored
     */
    private String location;
    /**
     * Image that would be used if no one was given
     */
    private String defaultImageName;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDefaultImageName() {
        return defaultImageName;
    }

    public void setDefaultImageName(String defaultImageName) {
        this.defaultImageName = defaultImageName;
    }
}
