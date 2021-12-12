package ru.project.fitstyle.controller.response.training;

import ru.project.fitstyle.model.dto.training.GroupTrainingWithUsersDto;
import ru.project.fitstyle.model.dto.training.PersonalTrainingWithUsersDto;

import java.util.List;

public class AllCoachTrainingsResponse {
    private final List<GroupTrainingWithUsersDto> groupTrainings;
    private final List<PersonalTrainingWithUsersDto> personalTrainings;

    public AllCoachTrainingsResponse(final List<GroupTrainingWithUsersDto> groupTrainings, final List<PersonalTrainingWithUsersDto> personalTrainings) {
        this.groupTrainings = groupTrainings;
        this.personalTrainings = personalTrainings;
    }

    public List<GroupTrainingWithUsersDto> getGroupTrainings() {
        return groupTrainings;
    }

    public List<PersonalTrainingWithUsersDto> getPersonalTrainings() {
        return personalTrainings;
    }
}
