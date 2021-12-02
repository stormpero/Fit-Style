package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.request.training.AddEditGroupTrainingRequest;
import ru.project.fitstyle.controller.request.training.AddEditPersonalTrainingRequest;
import ru.project.fitstyle.controller.request.training.AddEditTrainingRequest;
import ru.project.fitstyle.controller.response.other.SuccessMessage;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;
import ru.project.fitstyle.model.entity.training.Training;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.controller.response.training.AllTrainingsResponse;
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

    @GetMapping("/user")
    public ResponseEntity<AllTrainingsResponse> getFitUserTrainings() {
        FitUser fitUser = userService.getUserByEmail(authService.getEmail());
        return ResponseEntity.ok(
                new AllTrainingsResponse(fitUser.getGroupTrainings(), fitUser.getPersonalTrainings())
        );
    }

    @GetMapping("/coach/{id}")
    public ResponseEntity<AllTrainingsResponse> getCoachTrainings(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                new AllTrainingsResponse(trainingService.getGroupTrainingsByCoachId(id),
                        trainingService.getPersonalTrainingsByCoachId(id))
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
                userService.getUserByEmail(authService.getEmail()).getId(), trainingService.getTrainingById(request.getTrainingId()));
        trainingService.saveGroupTraining(groupTraining);
        return ResponseEntity.ok(
                new SuccessMessage("Success! Group training created!")
        );
    }

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/personal")
    public ResponseEntity<SuccessMessage> addPersonalTraining(@RequestBody AddEditPersonalTrainingRequest request) {
        PersonalTraining personalTraining = new PersonalTraining(request.getDate(), ETrainingStatus.LOGGED,
                userService.getUserByEmail(authService.getEmail()).getId());
        trainingService.savePersonalTraining(personalTraining);
        return ResponseEntity.ok(
                new SuccessMessage("Success! Personal training created!")
        );
    }
}
