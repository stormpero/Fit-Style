package ru.project.fitstyle.model.entity.training;

import ru.project.fitstyle.model.entity.user.FitUser;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "group_training")
public class GroupTraining {
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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "training_id", referencedColumnName = "id",
            nullable = false)
    private Training training;

    @ManyToMany(mappedBy = "groupTrainings",
            fetch = FetchType.LAZY)
    private List<FitUser> fitUsers = new ArrayList<>();

    public GroupTraining() {
    }

    public GroupTraining(Date startDate, Date endDate, ETrainingStatus status, Long coachId, Training training) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.coachId = coachId;
        this.training = training;
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

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<FitUser> getFitUsers() {
        return fitUsers;
    }

    public void setFitUsers(List<FitUser> fitUsers) {
        this.fitUsers = fitUsers;
    }
}
