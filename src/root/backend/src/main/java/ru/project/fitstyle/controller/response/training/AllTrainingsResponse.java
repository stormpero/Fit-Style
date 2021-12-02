package ru.project.fitstyle.controller.response.training;

import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;

import java.util.List;

public class AllTrainingsResponse {
    private final List<GroupTraining> groupTrainings;

    private final List<PersonalTraining> personalTrainings;

    public AllTrainingsResponse(List<GroupTraining> groupTrainings, List<PersonalTraining> personalTrainings) {
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
