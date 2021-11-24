package ru.project.fitstyle.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.payload.response.permission.PermissionResponse;
import ru.project.fitstyle.service.auth.AuthService;
import ru.project.fitstyle.service.user.FitUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/permission")
@PreAuthorize("hasRole('USER')")
public class PermissionController {

    private final FitUserService fitUserService;

    private final AuthService authServiceImpl;

    @Autowired
    public PermissionController(@Qualifier("fitUserServiceImpl") FitUserService fitUserService ,
                                @Qualifier("authServiceImpl") AuthService authServiceImpl) {
        this.fitUserService = fitUserService;
        this.authServiceImpl = authServiceImpl;
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getUserRoles() {
        return ResponseEntity.ok(
                new PermissionResponse(fitUserService.getFitUserRolesByEmail(authServiceImpl.getEmail())));
    }
}
