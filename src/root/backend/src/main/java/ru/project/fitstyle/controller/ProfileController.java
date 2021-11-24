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

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
@PreAuthorize("hasRole('USER')")
public class ProfileController {

    private final UserRepository userRepository;

    private final AuthService authServiceImpl;

    @Autowired
    public ProfileController (UserRepository userRepository,
                              @Qualifier("authServiceImpl") AuthService authServiceImpl) {
        this.userRepository = userRepository;
        this.authServiceImpl = authServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<UserProfileResponse> getUserProfileInfo() {
        FitUser fitUser = userRepository
                .findByEmail(authServiceImpl.getEmail())
                .orElseThrow(() ->
                        new ProfileException(EProfileError.NOT_FOUND));
        return ResponseEntity.ok(
                new UserProfileResponse(fitUser));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<UserProfileResponse> getUserProfileInfoById(@PathVariable("id") Long id) {
        Optional<FitUser> user = userRepository.findById(id);
        FitUser returnFitUser = user.orElseThrow(() ->
                new ProfileException(EProfileError.NOT_FOUND));
        return ResponseEntity.ok(
                new UserProfileResponse(returnFitUser));
    }
}
