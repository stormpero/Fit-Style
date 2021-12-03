package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.training.GroupTrainingInfo;
import ru.project.fitstyle.model.dto.training.PersonalTrainingInfo;
import ru.project.fitstyle.model.dto.training.TrainingNameInfo;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;
import ru.project.fitstyle.model.entity.training.Training;

import java.util.List;

public interface TrainingService {

    void saveTraining(Training training);

    Training getTrainingById(Long id);

    List<GroupTrainingInfo> getCoachGroupTrainingsByCoachId(Long id);

    List<PersonalTrainingInfo> getCoachPersonalTrainingsByCoachId(Long id);

    List<GroupTrainingInfo> getFitUserGroupTrainingsByFitUserEmail(String email);

    List<PersonalTrainingInfo> getFitUserPersonalTrainingsByFitUserEmail(String email);

    List<TrainingNameInfo> getTrainingNames();

    void saveGroupTraining(GroupTraining groupTraining);

    void savePersonalTraining(PersonalTraining personalTraining);
}
