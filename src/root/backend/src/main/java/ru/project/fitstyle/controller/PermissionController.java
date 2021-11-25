package ru.project.fitstyle.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.payload.response.permission.PermissionResponse;
import ru.project.fitstyle.service.auth.AuthService;
import ru.project.fitstyle.service.user.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/permission")
@PreAuthorize("hasRole('USER')")
public class PermissionController {

    private final UserService userService;

    private final AuthService authServiceImpl;

    @Autowired
    public PermissionController(@Qualifier("fitUserService") UserService userService,
                                @Qualifier("fitAuthService") AuthService authServiceImpl) {
        this.userService = userService;
        this.authServiceImpl = authServiceImpl;
    }

    @GetMapping("/roles")
    public ResponseEntity<PermissionResponse> getUserRoles() {
        return ResponseEntity.ok(
                new PermissionResponse(userService.getFitUserRolesByEmail(authServiceImpl.getEmail())));
    }
}
