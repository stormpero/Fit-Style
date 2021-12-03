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
import ru.project.fitstyle.controller.response.training.TrainingNamesResponse;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;
import ru.project.fitstyle.model.entity.training.Training;
import ru.project.fitstyle.controller.response.training.AllTrainingsResponse;
import ru.project.fitstyle.service.AuthService;
import ru.project.fitstyle.service.TrainingService;
import ru.project.fitstyle.service.UserService;

import java.util.Calendar;

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

        return ResponseEntity.ok(
                new AllTrainingsResponse(trainingService.getFitUserGroupTrainingsByFitUserEmail(authService.getEmail()),
                        trainingService.getFitUserPersonalTrainingsByFitUserEmail(authService.getEmail()))
        );
    }

    @GetMapping("/coach/{id}")
    public ResponseEntity<AllTrainingsResponse> getCoachTrainings(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                new AllTrainingsResponse(trainingService.getCoachGroupTrainingsByCoachId(id),
                        trainingService.getCoachPersonalTrainingsByCoachId(id))
        );
    }

    @GetMapping("/coach")
    public ResponseEntity<AllTrainingsResponse> getCoachTrainings() {
        return ResponseEntity.ok(
                new AllTrainingsResponse(trainingService.getCoachGroupTrainingsByCoachEmail(authService.getEmail()),
                        trainingService.getCoachPersonalTrainingsByCoachEmail(authService.getEmail()))
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
    @GetMapping("/{id}")
    public ResponseEntity<SuccessMessage> deleteTraining(@PathVariable("id") Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.ok(
                new SuccessMessage("Success! Training created!")
        );
    }

    @GetMapping("/name")
    public ResponseEntity<TrainingNamesResponse> getTrainingNames() {
        return ResponseEntity.ok(new TrainingNamesResponse(trainingService.getTrainingNames()));
    }

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/group")
    public ResponseEntity<SuccessMessage> addGroupTraining(@RequestBody AddEditGroupTrainingRequest request) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getDate());
        calendar.add(Calendar.HOUR, 1);

        GroupTraining groupTraining = new GroupTraining(request.getDate(), calendar.getTime(), ETrainingStatus.LOGGED,
                userService.getUserByEmail(authService.getEmail()).getId(), trainingService.getTrainingById(request.getTrainingId()));
        trainingService.saveGroupTraining(groupTraining);
        return ResponseEntity.ok(
                new SuccessMessage("Success! Group training created!")
        );
    }

    @PreAuthorize("hasRole('COACH')")
    @GetMapping("/group/{id}")
    public ResponseEntity<SuccessMessage> deleteGroupTraining(@PathVariable("id") Long id) {
        trainingService.deleteGroupTraining(id);
        return ResponseEntity.ok(
                new SuccessMessage("Success! Training created!")
        );
    }

//    @GetMapping("/group/sign/{id}")
//    public ResponseEntity<SuccessMessage> signForGroupTraining(@PathVariable("id") Long id) {
//
//    }

    @PreAuthorize("hasRole('COACH')")
    @PostMapping("/personal")
    public ResponseEntity<SuccessMessage> addPersonalTraining(@RequestBody AddEditPersonalTrainingRequest request) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getDate());
        calendar.add(Calendar.HOUR, 1);

        PersonalTraining personalTraining = new PersonalTraining(request.getDate(), calendar.getTime(), ETrainingStatus.LOGGED,
                userService.getUserByEmail(authService.getEmail()).getId());
        trainingService.savePersonalTraining(personalTraining);
        return ResponseEntity.ok(
                new SuccessMessage("Success! Personal training created!")
        );
    }

//    @GetMapping("/personal/sign/{id}")
//    public ResponseEntity<SuccessMessage> signForPersonalTraining(@PathVariable("id") Long id) {
//
//    }

    @PreAuthorize("hasRole('COACH')")
    @GetMapping("/personal/{id}")
    public ResponseEntity<SuccessMessage> deletePersonalTraining(@PathVariable("id") Long id) {
        trainingService.deletePersonalTraining(id);
        return ResponseEntity.ok(
                new SuccessMessage("Success! Training created!")
        );
    }
}
