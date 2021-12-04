package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.request.profile.ChangeBalanceRequest;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.model.entity.user.FitUser;
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
    public ProfileController (UserService userService,
                              AuthService authService,
                              StorageService imageStorageService) {
        this.userService = userService;
        this.authService = authService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping()
    public ResponseEntity<UserProfileResponse> getUserProfileInfo() {
        return ResponseEntity.ok(new UserProfileResponse(userService.getFitUserInfoByEmail(authService.getEmail()),
                userService.getSubscriptionResponseInfoByEmail(authService.getEmail()), userService.getUserRolesByEmail(authService.getEmail())));
    }

    @GetMapping ("/user_image")
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
        return ResponseEntity.ok(new UserProfileResponse(userService.getFitUserInfoById(id),
                userService.getSubscriptionResponseInfoById(id), userService.getUserRolesById(id)));
    };


    @PatchMapping("/change_balance")
    public ResponseEntity<SuccessMessage> changeBalance(@RequestBody ChangeBalanceRequest changeBalanceRequest) {
        userService.changeBalance(userService.getUserByEmail(authService.getEmail()), changeBalanceRequest.getSummary());
        return ResponseEntity.ok(
                new SuccessMessage("Balance changed successfully!")
        );
    }
}
