package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.exception.profile.EProfileError;
import ru.project.fitstyle.exception.profile.ProfileException;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.payload.response.profile.UserProfileResponse;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.service.auth.AuthService;
import ru.project.fitstyle.service.profile.ProfileService;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
@PreAuthorize("hasRole('USER')")
public class ProfileController {

    private final ProfileService profileService;

    private final AuthService authService;

    @Autowired
    public ProfileController (@Qualifier("profileServiceImpl") ProfileService profileService,
                              @Qualifier("authServiceImpl") AuthService authService) {
        this.profileService = profileService;
        this.authService = authService;
    }

    @GetMapping()
    public ResponseEntity<UserProfileResponse> getUserProfileInfo() {
        FitUser fitUser = profileService.getUserByEmail(authService.getEmail());
        return ResponseEntity.ok(
                new UserProfileResponse(fitUser));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<UserProfileResponse> getUserProfileInfoById(@PathVariable("id") Long id) {
        FitUser fitUser = profileService.getUserById(id);
        return ResponseEntity.ok(
                new UserProfileResponse(fitUser));
    }
}
