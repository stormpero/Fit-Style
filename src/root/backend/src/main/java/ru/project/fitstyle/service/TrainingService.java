package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.training.GroupTraining;
import ru.project.fitstyle.model.dto.training.PersonalTraining;
import ru.project.fitstyle.model.dto.training.Training;

import java.util.List;

public interface TrainingService {

    void saveTraining(Training training);

    Training getTrainingById(Long id);

    List<GroupTraining> getGroupTrainingsByCoachId(Long id);

    List<PersonalTraining> getPersonalTrainingsByCoachId(Long id);

    void saveGroupTraining(GroupTraining groupTraining);

    void savePersonalTraining(PersonalTraining personalTraining);
}
