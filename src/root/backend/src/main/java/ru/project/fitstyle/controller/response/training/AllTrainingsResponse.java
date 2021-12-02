package ru.project.fitstyle.controller.response.training;

import ru.project.fitstyle.model.dto.training.GroupTrainingInfo;
import ru.project.fitstyle.model.dto.training.PersonalTrainingInfo;
import ru.project.fitstyle.model.entity.training.GroupTraining;
import ru.project.fitstyle.model.entity.training.PersonalTraining;

import java.util.List;

public class AllTrainingsResponse {
    private final List<GroupTrainingInfo> groupTrainings;

    private final List<PersonalTrainingInfo> personalTrainings;

    public AllTrainingsResponse(List<GroupTrainingInfo> groupTrainings, List<PersonalTrainingInfo> personalTrainings) {
        this.groupTrainings = groupTrainings;
        this.personalTrainings = personalTrainings;
    }

    public List<GroupTrainingInfo> getGroupTrainings() {
        return groupTrainings;
    }

    public List<PersonalTrainingInfo> getPersonalTrainings() {
        return personalTrainings;
    }
}
