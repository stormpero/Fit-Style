package ru.project.fitstyle.service.impl;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.service.AuthService;
import ru.project.fitstyle.service.exception.token.AnonymousTokenException;

@Service
public class FitAuthService implements AuthService {
    @Override
    public String getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        else {
            throw new AnonymousTokenException("Anonymous token!");
        }
    }
}
