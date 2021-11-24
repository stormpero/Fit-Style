package ru.project.fitstyle.payload.response.training;

import ru.project.fitstyle.model.training.GroupTraining;
import ru.project.fitstyle.model.training.PersonalTraining;

import java.util.Set;

public class TrainingsResponse {
    private final Set<GroupTraining> groupTrainings;

    private final Set<PersonalTraining> personalTrainings;

    public TrainingsResponse(Set<GroupTraining> groupTrainings, Set<PersonalTraining> personalTrainings) {
        this.groupTrainings = groupTrainings;
        this.personalTrainings = personalTrainings;
    }

    public Set<GroupTraining> getGroupTrainings() {
        return groupTrainings;
    }

    public Set<PersonalTraining> getPersonalTrainings() {
        return personalTrainings;
    }
}
