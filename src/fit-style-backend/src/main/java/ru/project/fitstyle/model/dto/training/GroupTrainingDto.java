package ru.project.fitstyle.model.dto.training;

import ru.project.fitstyle.model.dto.user.FitUserFullNameDto;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;

import java.util.Date;

public class GroupTrainingDto {
    private final Long id;

    private final Date startDate;

    private final Date endDate;

    private final ETrainingStatus status;

    private final FitUserFullNameDto fitUser;

    private final String title;

    private final int numberOfUsers;

    public GroupTrainingDto(final Long id, final Date startDate, final Date endDate, final ETrainingStatus status,
                            final Long coachId, final String name, final String surname, final String patronymic,
                            final String title, final int numberOfUsers) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.fitUser = new FitUserFullNameDto(coachId, name, surname, patronymic);
        this.title = title;
        this.numberOfUsers=numberOfUsers;
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

    public String getTitle() {
        return title;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }
}
