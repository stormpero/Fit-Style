package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.model.dto.user.FitUser;
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
    public ProfileController (@Qualifier("fitUserService") UserService userService,
                              @Qualifier("fitAuthService") AuthService authService,
                              @Qualifier("imageStorageService") StorageService imageStorageService) {
        this.userService = userService;
        this.authService = authService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping()
    public ResponseEntity<UserProfileResponse> getUserProfileInfo() {
        FitUser fitUser = userService.getUserByEmail(authService.getEmail());
        return ResponseEntity.ok(
                new UserProfileResponse(fitUser));
    }

    @GetMapping ("/user_profile_image")
    public ResponseEntity<Resource> getUserProfileImage() {
        FitUser fitUser = userService.getUserByEmail(authService.getEmail());
        Resource resource = imageStorageService.loadAsResource(fitUser.getImgURL());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<UserProfileResponse> getUserProfileInfoById(@PathVariable("id") Long id) {
        FitUser fitUser = userService.getUserById(id);
        return ResponseEntity.ok(
                new UserProfileResponse(fitUser));
    }
}
