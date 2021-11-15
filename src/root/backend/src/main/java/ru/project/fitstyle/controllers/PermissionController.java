package ru.project.fitstyle.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.exception.permission.EPermissionError;
import ru.project.fitstyle.exception.permission.PermissionException;
import ru.project.fitstyle.models.user.User;
import ru.project.fitstyle.payload.response.permission.PermissionResponse;
import ru.project.fitstyle.repository.UserRepository;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/permission")
@PreAuthorize("hasRole('USER')")
public class PermissionController {

    UserRepository userRepository;

    @Autowired
    public PermissionController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/roles")
    public ResponseEntity<PermissionResponse> getUserRoles(@RequestParam("id") Long id) {
        Optional<User> user = userRepository.findById(id);
        User returnUser = user.orElseThrow(() ->
                new PermissionException(EPermissionError.NOT_FOUND));
        return ResponseEntity.ok(
                new PermissionResponse(returnUser.getRoles()));
    }
}
