package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.training.GroupTrainingDto;
import ru.project.fitstyle.model.dto.training.PersonalTrainingDto;
import ru.project.fitstyle.model.dto.training.TrainingNameDto;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;
import ru.project.fitstyle.model.entity.training.Training;

import java.util.List;

public interface TrainingService {

    void saveTraining(Training training);

    void saveGroupTraining(GroupTraining groupTraining);

    void savePersonalTraining(PersonalTraining personalTraining);

    void deleteTraining(Long id);

    void deleteGroupTraining(Long id);

    void deletePersonalTraining(Long id);

    void signForGroupTraining(Long trainingId, String fitUserEmail);

    void signForPersonalTraining(Long trainingId, String fitUserEmail);

    Training getTrainingById(Long id);

    List<GroupTrainingDto> getCoachGroupTrainingsByCoachId(Long id);

    List<PersonalTrainingDto> getCoachPersonalTrainingsByCoachId(Long id);

    List<GroupTrainingDto> getCoachGroupTrainingsByCoachEmail(String email);

    List<PersonalTrainingDto> getCoachPersonalTrainingsByCoachEmail(String email);

    List<GroupTrainingDto> getFitUserGroupTrainingsByFitUserEmail(String email);

    List<PersonalTrainingDto> getFitUserPersonalTrainingsByFitUserEmail(String email);

    List<TrainingNameDto> getTrainingNames();
}
