package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.response.profile.CoachInfoResponse;
import ru.project.fitstyle.model.dto.user.FitUser;
import ru.project.fitstyle.service.UserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/coach")
@PreAuthorize("hasRole('USER')")
public class CoachController {
    private final UserService userService;

    @Autowired
    public CoachController(@Qualifier("fitUserService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoachInfoResponse> getCoachInfoById(@PathVariable("id") Long id) {
        FitUser fitUser = userService.getUserById(id);
        return ResponseEntity.ok(
                new CoachInfoResponse(fitUser.getName(), fitUser.getSurname(), fitUser.getPatronymic()));
    }
}
