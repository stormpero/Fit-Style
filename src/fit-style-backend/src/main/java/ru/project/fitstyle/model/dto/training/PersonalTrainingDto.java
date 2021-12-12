package ru.project.fitstyle.model.dto.training;

import ru.project.fitstyle.model.dto.user.FitUserFullNameDto;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;

import java.util.Date;

public class PersonalTrainingDto {
    private final Long id;

    private final Date startDate;

    private final Date endDate;

    private final ETrainingStatus status;

    private final FitUserFullNameDto fitUser;

    public PersonalTrainingDto(final Long id, final Date startDate, final Date endDate,final ETrainingStatus status,
                               final Long coachId, final String name, final String surname, final String patronymic) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.fitUser = new FitUserFullNameDto(coachId, name, surname, patronymic);
    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public ETrainingStatus getStatus() {
        return status;
    }

    public FitUserFullNameDto getFitUser() {
        return fitUser;
    }
}
