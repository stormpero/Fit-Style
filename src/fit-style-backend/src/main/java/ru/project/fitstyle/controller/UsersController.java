package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.response.coach.AllCoachesResponse;
import ru.project.fitstyle.controller.response.fitusers.AllFitUserResponse;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<AllFitUserResponse> getAllFitUsers() {
        return ResponseEntity.ok(
                new AllFitUserResponse(userService.getAllUsers())
        );
    }
}
