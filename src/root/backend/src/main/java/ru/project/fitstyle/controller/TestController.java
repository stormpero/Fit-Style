package ru.project.fitstyle.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.exception.auth.refresh.ERefreshTokenError;
import ru.project.fitstyle.exception.auth.refresh.RefreshTokenException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('COACH')")
    public String userAccess() {
        return "Добро пожаловать, ";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('COACH')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/exception_check")
    public void exceptionCheck()
    {
        throw new RefreshTokenException("f3tdrgsdfgsd", ERefreshTokenError.NOT_FOUND);
    }
}
