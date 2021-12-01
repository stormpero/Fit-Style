package ru.project.fitstyle.controller.response.training;

import ru.project.fitstyle.model.dto.training.GroupTraining;
import ru.project.fitstyle.model.dto.training.PersonalTraining;

import java.util.List;
import java.util.Set;

public class TrainingsResponse {
    private final List<GroupTraining> groupTrainings;

    private final List<PersonalTraining> personalTrainings;

    public TrainingsResponse(List<GroupTraining> groupTrainings, List<PersonalTraining> personalTrainings) {
        this.groupTrainings = groupTrainings;
        this.personalTrainings = personalTrainings;
    }

    public List<GroupTraining> getGroupTrainings() {
        return groupTrainings;
    }

    public List<PersonalTraining> getPersonalTrainings() {
        return personalTrainings;
    }
}
