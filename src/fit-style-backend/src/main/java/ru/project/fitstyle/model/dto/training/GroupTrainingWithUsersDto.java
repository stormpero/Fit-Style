package ru.project.fitstyle.model.dto.training;

import ru.project.fitstyle.model.dto.user.FitUserFullNameDto;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;

import java.util.Date;
import java.util.List;

public class GroupTrainingWithUsersDto {
    private final Long id;

    private final Date startDate;

    private final Date endDate;

    private final ETrainingStatus status;

    private final String title;

    private final List<FitUserFullNameDto> fitUser;

    private final long numberOfUsers;

    public GroupTrainingWithUsersDto(Long id, Date startDate, Date endDate, ETrainingStatus status, String title, List<FitUserFullNameDto> fitUser, long numberOfUsers) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.title = title;
        this.fitUser = fitUser;
        this.numberOfUsers = numberOfUsers;
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

    public String getTitle() {
        return title;
    }

    public List<FitUserFullNameDto> getFitUser() {
        return fitUser;
    }

    public long getNumberOfUsers() {
        return numberOfUsers;
    }
}
