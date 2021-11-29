package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.dao.GroupTrainingRepository;
import ru.project.fitstyle.model.dao.PersonalTrainingRepository;
import ru.project.fitstyle.model.dao.TrainingRepository;
import ru.project.fitstyle.model.dto.training.GroupTraining;
import ru.project.fitstyle.model.dto.training.PersonalTraining;
import ru.project.fitstyle.model.dto.training.Training;
import ru.project.fitstyle.service.TrainingService;

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
}
