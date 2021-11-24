package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.payload.response.training.TrainingsResponse;
import ru.project.fitstyle.service.auth.AuthService;
import ru.project.fitstyle.service.user.FitUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/training")
@PreAuthorize("hasRole('USER')")
public class TrainingController {
    private final AuthService authService;
    private final FitUserService fitUserService;

    @Autowired
    public TrainingController(@Qualifier("authServiceImpl") AuthService authService,
                              @Qualifier("fitUserServiceImpl") FitUserService fitUserService) {
        this.authService = authService;
        this.fitUserService = fitUserService;
    }

    @GetMapping()
    public ResponseEntity<TrainingsResponse> getFitUserTrainings() {
        FitUser fitUser = fitUserService.getUserByEmail(authService.getEmail());
        return ResponseEntity.ok(
                new TrainingsResponse(fitUser.getGroupTrainings(), fitUser.getPersonalTrainings())
        );
    }
}
