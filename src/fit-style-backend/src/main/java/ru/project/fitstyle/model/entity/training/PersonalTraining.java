package ru.project.fitstyle.model.entity.training;

import ru.project.fitstyle.model.entity.user.FitUser;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personal_training")
public class PersonalTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "start_date",
            nullable = false)
    private Date startDate;

    @Column(name = "end_date",
            nullable = false)
    private Date endDate;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10,
            nullable = false)
    private ETrainingStatus status;

    @Column(name = "coach_id",
            nullable = false)
    private Long coachId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fit_user_id", referencedColumnName = "id",
            nullable = false)
    private FitUser fitUser;

    public PersonalTraining() {
    }

    public PersonalTraining(Date startDate, Date endDate, ETrainingStatus status, Long coachId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.coachId = coachId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ETrainingStatus getStatus() {
        return status;
    }

    public void setStatus(ETrainingStatus status) {
        this.status = status;
    }

    public FitUser getUser() {
        return fitUser;
    }

    public void setUser(FitUser fitUser) {
        this.fitUser = fitUser;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoach_id(Long coachId) {
        this.coachId = coachId;
    }
}
