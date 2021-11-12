package ru.project.fitstyle.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.models.user.User;
import ru.project.fitstyle.payload.response.permission.PermissionResponse;
import ru.project.fitstyle.payload.response.utils.MessageResponse;
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
    public ResponseEntity<?> getUserRoles(@RequestParam("id") Long id)
    {
        Optional<User> user = userRepository.findById(id);
        User returnUser = user
                .orElse(null);
        if(returnUser != null) {
            return ResponseEntity.ok(
                    new PermissionResponse(returnUser.getRoles()));
        }
        else {
            return ResponseEntity.badRequest().
                    body(
                            new MessageResponse("Error: user with that id doesn't exist!"));
        }
    }
}
