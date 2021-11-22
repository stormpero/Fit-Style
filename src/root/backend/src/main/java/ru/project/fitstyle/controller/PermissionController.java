package ru.project.fitstyle.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.exception.permission.EPermissionError;
import ru.project.fitstyle.exception.permission.PermissionException;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.payload.response.permission.PermissionResponse;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.service.AuthService;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/permission")
@PreAuthorize("hasRole('USER')")
public class PermissionController {

    private final UserRepository userRepository;

    private final AuthService authService;

    @Autowired
    public PermissionController(UserRepository userRepository , AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getUserRoles() {
        Optional<FitUser> user = userRepository.findByEmail(authService.getAuthentication().getName());
        FitUser returnFitUser = user.orElseThrow(() ->
                new PermissionException(EPermissionError.NOT_FOUND));
        return ResponseEntity.ok(
                new PermissionResponse(returnFitUser.getRoles()));
    }
}
