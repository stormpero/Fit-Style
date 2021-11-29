package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.request.training.AddEditGroupTrainingRequest;
import ru.project.fitstyle.controller.request.training.AddEditPersonalTrainingRequest;
import ru.project.fitstyle.controller.request.training.AddEditTrainingRequest;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.model.dto.training.ETrainingStatus;
import ru.project.fitstyle.model.dto.training.GroupTraining;
import ru.project.fitstyle.model.dto.training.PersonalTraining;
import ru.project.fitstyle.model.dto.training.Training;
import ru.project.fitstyle.model.dto.user.FitUser;
import ru.project.fitstyle.controller.response.training.TrainingsResponse;
import ru.project.fitstyle.service.AuthService;
import ru.project.fitstyle.service.TrainingService;
import ru.project.fitstyle.service.UserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/training")
@PreAuthorize("hasRole('USER')")
public class TrainingController {
    private final AuthService authService;
    private final UserService userService;
    private final TrainingService trainingService;

    @Autowired
    public TrainingController(@Qualifier("fitAuthService") AuthService authService,
                              @Qualifier("fitUserService") UserService userService,
                              @Qualifier("fitTrainingService") TrainingService trainingService) {
        this.authService = authService;
        this.userService = userService;
        this.trainingService = trainingService;
    }

    @GetMapping()
    public ResponseEntity<TrainingsResponse> getFitUserTrainings() {
        FitUser fitUser = userService.getUserByEmail(authService.getEmail());
        return ResponseEntity.ok(
                new TrainingsResponse(fitUser.getGroupTrainings(), fitUser.getPersonalTrainings())
        );
    }

    @PreAuthorize("hasRole('COACH')")
    @PostMapping()
    public ResponseEntity<SuccessMessage> addTraining(@RequestBody AddEditTrainingRequest request) {
        Training training = new Training(request.getName());
        trainingService.saveTraining(training);
        return ResponseEntity.ok(
                new SuccessMessage("Success! Training created!")
        );
    }

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/group")
    public ResponseEntity<SuccessMessage> addGroupTraining(@RequestBody AddEditGroupTrainingRequest request) {
        GroupTraining groupTraining = new GroupTraining(request.getDate(), ETrainingStatus.LOGGED,
                userService.getUserByEmail(authService.getEmail()).getId());
        return ResponseEntity.ok(
                new SuccessMessage("Success! Group training created!")
        );
    }

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/personal")
    public ResponseEntity<SuccessMessage> addPersonalTraining(@RequestBody AddEditPersonalTrainingRequest request) {
        PersonalTraining personalTraining = new PersonalTraining(request.getDate(), ETrainingStatus.LOGGED,
                userService.getUserByEmail(authService.getEmail()).getId());
        return ResponseEntity.ok(
                new SuccessMessage("Success! Personal training created!")
        );
    }
}
