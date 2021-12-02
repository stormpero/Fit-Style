package ru.project.fitstyle.model.dto.training;

import ru.project.fitstyle.model.dto.user.CoachInfo;
import ru.project.fitstyle.model.entity.training.ETrainingStatus;

import java.util.Date;

public class PersonalTrainingInfo {
    private final Long id;

    private final Date startDate;

    private final Date endDate;

    private final ETrainingStatus status;

    private final CoachInfo coach;

    public PersonalTrainingInfo(Long id, Date startDate, Date endDate, ETrainingStatus status,
                                Long coachId, String name, String surname, String patronymic) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.coach = new CoachInfo(coachId, name, surname, patronymic);
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

    public CoachInfo getCoach() {
        return coach;
    }
}
