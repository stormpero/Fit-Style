package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.training.GroupTrainingDto;
import ru.project.fitstyle.model.dto.training.PersonalTrainingDto;
import ru.project.fitstyle.model.dto.training.TrainingNameDto;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;
import ru.project.fitstyle.model.entity.training.Training;

import java.util.List;

public interface TrainingService {

    void saveTraining(final Training training);

    void saveGroupTraining(final GroupTraining groupTraining);

    void savePersonalTraining(final PersonalTraining personalTraining);

    void deleteTraining(final Long id);

    void deleteGroupTraining(final Long id);

    void deletePersonalTraining(final Long id);

    GroupTraining getGroupTrainingById(final Long id);

    PersonalTraining getPersonalTrainingById(final Long id);

    Training getTrainingById(final Long id);

    List<GroupTrainingDto> getCoachGroupTrainingsByCoachId(final Long id);

    List<PersonalTrainingDto> getCoachPersonalTrainingsByCoachId(final Long id);

    List<GroupTrainingDto> getCoachGroupTrainingsByCoachEmail(final String email);

    List<PersonalTrainingDto> getCoachPersonalTrainingsByCoachEmail(final String email);

    List<GroupTrainingDto> getFitUserGroupTrainingsByFitUserEmail(final String email);

    List<PersonalTrainingDto> getFitUserPersonalTrainingsByFitUserEmail(final String email);

    List<TrainingNameDto> getTrainingNames();
}
