package ru.project.fitstyle.service.auth;

import org.springframework.security.core.Authentication;

public interface AuthService {

    Authentication getAuthentication();
}
