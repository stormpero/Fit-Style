package ru.project.fitstyle.controller.response.training;

import ru.project.fitstyle.model.dto.training.GroupTrainingDto;
import ru.project.fitstyle.model.dto.training.PersonalTrainingDto;

import java.util.List;

public class AllTrainingsResponse {
    private final List<GroupTrainingDto> groupTrainings;

    private final List<PersonalTrainingDto> personalTrainings;

    public AllTrainingsResponse(List<GroupTrainingDto> groupTrainings, List<PersonalTrainingDto> personalTrainings) {
        this.groupTrainings = groupTrainings;
        this.personalTrainings = personalTrainings;
    }

    public List<GroupTrainingDto> getGroupTrainings() {
        return groupTrainings;
    }

    public List<PersonalTrainingDto> getPersonalTrainings() {
        return personalTrainings;
    }
}
