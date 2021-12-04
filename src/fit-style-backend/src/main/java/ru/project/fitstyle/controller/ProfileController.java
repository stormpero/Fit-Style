package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.request.profile.ChangeBalanceRequest;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.controller.response.profile.UserProfileResponse;
import ru.project.fitstyle.service.AuthService;
import ru.project.fitstyle.service.StorageService;
import ru.project.fitstyle.service.UserService;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
@PreAuthorize("hasRole('USER')")
public class ProfileController {

    private final UserService userService;

    private final AuthService authService;

    private final StorageService imageStorageService;

    @Autowired
    public ProfileController (final UserService userService,
                              final AuthService authService,
                              final StorageService imageStorageService) {
        this.userService = userService;
        this.authService = authService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping()
    public ResponseEntity<UserProfileResponse> getUserProfileInfo() {
        return ResponseEntity.ok(new UserProfileResponse(userService.getFitUserInfoByEmail(authService.getEmail()),
                userService.getSubscriptionResponseInfoByEmail(authService.getEmail()), userService.getUserRolesByEmail(authService.getEmail())));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<UserProfileResponse> getUserProfileInfoById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(new UserProfileResponse(userService.getFitUserInfoById(id),
                userService.getSubscriptionResponseInfoById(id), userService.getUserRolesById(id)));
    };


    @PatchMapping("/change_balance")
    public ResponseEntity<SuccessMessage> changeBalance(@RequestBody final ChangeBalanceRequest changeBalanceRequest) {
        userService.changeBalance(userService.getUserByEmail(authService.getEmail()), changeBalanceRequest.getSummary());
        return ResponseEntity.ok(
                new SuccessMessage("Balance changed successfully!")
        );
    }
}
