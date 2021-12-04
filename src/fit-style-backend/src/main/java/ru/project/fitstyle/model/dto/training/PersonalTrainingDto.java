package ru.project.fitstyle.model.dto.training;

import ru.project.fitstyle.model.dto.user.CoachDto;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;

import java.util.Date;

public class PersonalTrainingDto {
    private final Long id;

    private final Date startDate;

    private final Date endDate;

    private final ETrainingStatus status;

    private final CoachDto coach;

    public PersonalTrainingDto(Long id, Date startDate, Date endDate, ETrainingStatus status,
                               Long coachId, String name, String surname, String patronymic) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.coach = new CoachDto(coachId, name, surname, patronymic);
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
}
