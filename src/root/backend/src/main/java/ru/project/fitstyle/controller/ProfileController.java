package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.exception.profile.EProfileError;
import ru.project.fitstyle.exception.profile.ProfileException;
import ru.project.fitstyle.model.user.User;
import ru.project.fitstyle.payload.response.profile.UserProfileResponse;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.service.AuthService;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
@PreAuthorize("hasRole('USER')")
public class ProfileController {

    UserRepository userRepository;

    AuthService authService;

    @Autowired
    public ProfileController (UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @GetMapping()
    public ResponseEntity<UserProfileResponse> getUserProfileInfo() {
        User user = userRepository
                .findByEmail(authService.getAuthentication().getName())
                .orElseThrow(() ->
                        new ProfileException(EProfileError.NOT_FOUND));
        return ResponseEntity.ok(
                new UserProfileResponse(user));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<UserProfileResponse> getUserProfileInfoById(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        User returnUser = user.orElseThrow(() ->
                new ProfileException(EProfileError.NOT_FOUND));
        return ResponseEntity.ok(
                new UserProfileResponse(returnUser));
    }
}
