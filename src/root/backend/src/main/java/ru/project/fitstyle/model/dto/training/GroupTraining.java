package ru.project.fitstyle.model.dto.training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.project.fitstyle.model.dto.user.FitUser;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_training")
public class GroupTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "date",
            nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10,
            nullable = false)
    private ETrainingStatus status;

    @Column(name = "coach_id",
            nullable = false)
    private Long coachId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "training_id", referencedColumnName = "id",
            nullable = false)
    private Training training;

    @ManyToMany(mappedBy = "groupTrainings",
            fetch = FetchType.LAZY)
    private Set<FitUser> fitUsers = new HashSet<>();

    public GroupTraining() {
    }

    public GroupTraining(Date date, ETrainingStatus status, Long coachId, Training training) {
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @JsonIgnore
    public Set<FitUser> getFitUsers() {
        return fitUsers;
    }

    public void setFitUsers(Set<FitUser> fitUsers) {
        this.fitUsers = fitUsers;
    }
}
