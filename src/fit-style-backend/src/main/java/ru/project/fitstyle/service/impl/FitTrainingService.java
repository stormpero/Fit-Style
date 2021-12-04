package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.dao.GroupTrainingRepository;
import ru.project.fitstyle.model.dao.PersonalTrainingRepository;
import ru.project.fitstyle.model.dao.TrainingRepository;
import ru.project.fitstyle.model.dto.training.GroupTrainingDto;
import ru.project.fitstyle.model.dto.training.PersonalTrainingDto;
import ru.project.fitstyle.model.dto.training.TrainingNameDto;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;
import ru.project.fitstyle.model.entity.training.Training;
import ru.project.fitstyle.service.TrainingService;
import ru.project.fitstyle.service.exception.training.TrainingNotFoundException;

import java.util.List;

@Service
public class FitTrainingService implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final GroupTrainingRepository groupTrainingRepository;
    private final PersonalTrainingRepository personalTrainingRepository;

    @Autowired
    public FitTrainingService(TrainingRepository trainingRepository,
                              GroupTrainingRepository groupTrainingRepository,
                              PersonalTrainingRepository personalTrainingRepository) {
        this.trainingRepository = trainingRepository;
        this.groupTrainingRepository = groupTrainingRepository;
        this.personalTrainingRepository = personalTrainingRepository;
    }

    @Override
    public void saveTraining(Training training) {
        trainingRepository.save(training);
    }

    @Override
    public void saveGroupTraining(GroupTraining groupTraining) {
        groupTrainingRepository.save(groupTraining);
    }

    @Override
    public void savePersonalTraining(PersonalTraining personalTraining) {
        personalTrainingRepository.save(personalTraining);
    }

    @Override
    public void deleteTraining(Long id) {
        trainingRepository.deleteById(id);
    }

    @Override
    public void deleteGroupTraining(Long id) {
        groupTrainingRepository.deleteById(id);
    }

    @Override
    public void deletePersonalTraining(Long id) {
        personalTrainingRepository.deleteById(id);
    }

    @Override
    public void signForGroupTraining(Long trainingId, String fitUserEmail) {
        GroupTraining groupTraining = groupTrainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingNotFoundException("Group training with that id cannot be found!"));
//        groupTrainingRepository.
//        FitUser fitUser =
//        groupTraining.getFitUsers().add();
    }

    @Override
    public void signForPersonalTraining(Long trainingId, String fitUserEmail) {

    }

    @Override
    public Training getTrainingById(Long id) {
        return trainingRepository.findById(id)
                .orElseThrow(() -> new TrainingNotFoundException("Training with that email cannot be found!"));
    }

    @Override
    public List<GroupTrainingDto> getCoachGroupTrainingsByCoachId(Long id) {
        return groupTrainingRepository.findAllCoachTrainingsWithCoachId(id);
    }

    @Override
    public List<PersonalTrainingDto> getCoachPersonalTrainingsByCoachId(Long id) {
        return personalTrainingRepository.findAllCoachTrainingsWithCoachId(id);
    }

    @Override
    public List<GroupTrainingDto> getCoachGroupTrainingsByCoachEmail(String email) {
        return groupTrainingRepository.findAllCoachTrainingsWithCoachEmail(email);
    }

    @Override
    public List<PersonalTrainingDto> getCoachPersonalTrainingsByCoachEmail(String email) {
        return personalTrainingRepository.findAllCoachTrainingsWithCoachEmail(email);
    }

    @Override
    public List<GroupTrainingDto> getFitUserGroupTrainingsByFitUserEmail(String email) {
        return groupTrainingRepository.findAllFitUserTrainingsWithFitUserEmail(email);
    }

    @Override
    public List<PersonalTrainingDto> getFitUserPersonalTrainingsByFitUserEmail(String email) {
        return personalTrainingRepository.findAllFitUserTrainingsWithFitUserEmail(email);
    }

    @Override
    public List<TrainingNameDto> getTrainingNames() {
        return trainingRepository.findAllTrainingNameInfo();
    }
}
