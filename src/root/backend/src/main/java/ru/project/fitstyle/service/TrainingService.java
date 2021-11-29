package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.training.GroupTraining;
import ru.project.fitstyle.model.dto.training.PersonalTraining;
import ru.project.fitstyle.model.dto.training.Training;

public interface TrainingService {

    void saveTraining(Training training);

    Training getTrainingById(Long id);

    void saveGroupTraining(GroupTraining groupTraining);

    void savePersonalTraining(PersonalTraining personalTraining);
}
