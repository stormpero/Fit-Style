package ru.project.fitstyle.service.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    @Override
    public String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
