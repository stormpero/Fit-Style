package ru.project.fitstyle.model.dto.training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.project.fitstyle.model.dto.user.FitUser;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(	name = "personal_training")
public class PersonalTraining {
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fit_user_id", referencedColumnName = "id",
            nullable = false)
    private FitUser fitUser;

    public PersonalTraining() {
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

    @JsonIgnore
    public FitUser getUser() {
        return fitUser;
    }
    @JsonIgnore
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
