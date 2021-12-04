package ru.project.fitstyle.model.dto.training;

import ru.project.fitstyle.model.dto.user.CoachDto;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;

import java.util.Date;

public class GroupTrainingDto {
    private final Long id;

    private final Date startDate;

    private final Date endDate;

    private final ETrainingStatus status;

    private final CoachDto coach;

    private final String title;

    public GroupTrainingDto(Long id, Date startDate, Date endDate, ETrainingStatus status,
                            Long coachId, String name, String surname, String patronymic,
                            String title) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.coach = new CoachDto(coachId, name, surname, patronymic);
        this.title = title;
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

    public CoachDto getCoach() {
        return coach;
    }

    public String getTitle() {
        return title;
    }
}
