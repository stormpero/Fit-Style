package ru.project.fitstyle.model.dto.training;

import ru.project.fitstyle.model.dto.user.FitUserFullNameDto;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;

import java.util.Date;

public class PersonalTrainingWithUsersDto {
    private final Long id;

    private final Date startDate;

    private final Date endDate;

    private final ETrainingStatus status;

    private final FitUserFullNameDto fitUser;

    public PersonalTrainingWithUsersDto(Long id, Date startDate, Date endDate, ETrainingStatus status, FitUserFullNameDto fitUser) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.fitUser = fitUser;
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
