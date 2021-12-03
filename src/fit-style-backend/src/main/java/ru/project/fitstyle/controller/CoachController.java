package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.response.coach.AllCoachesResponse;
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


    @GetMapping()
    public ResponseEntity<AllCoachesResponse> getAllCoaches() {
        return ResponseEntity.ok(
                new AllCoachesResponse(userService.getCoaches())
        );
    }
}
