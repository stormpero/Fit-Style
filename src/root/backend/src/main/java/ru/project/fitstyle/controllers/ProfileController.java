package ru.project.fitstyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.models.user.User;
import ru.project.fitstyle.payload.response.utils.MessageResponse;
import ru.project.fitstyle.payload.response.user.UserProfileResponse;
import ru.project.fitstyle.repository.UserRepository;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
@PreAuthorize("hasRole('USER')")
public class ProfileController {

    UserRepository userRepository;

    @Autowired
    public ProfileController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getUserProfileInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository
                .findByEmail(authentication.getName())
                .orElse(null);
        if(user != null) {
            return ResponseEntity.ok(
                    new UserProfileResponse(user));
        }
        else {
            return ResponseEntity.badRequest()
                    .body(
                            new MessageResponse("User not found!")
                    );
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> getUserProfileInfoById(@PathVariable("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        User returnUser = user
                .orElse(null);
        if(returnUser != null) {
            return ResponseEntity.ok(
                    new UserProfileResponse(returnUser));
        }
        else {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: user with that id doesn't exist!"));
        }
    }
}
