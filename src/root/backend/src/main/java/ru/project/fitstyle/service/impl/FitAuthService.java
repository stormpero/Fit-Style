package ru.project.fitstyle.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.service.AuthService;

@Service
public class FitAuthService implements AuthService {
    @Override
    public String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
