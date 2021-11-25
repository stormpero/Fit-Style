package ru.project.fitstyle.model.training;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.project.fitstyle.model.user.FitUser;

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

    @JsonIgnore
    @ManyToMany(mappedBy = "groupTrainings",
            fetch = FetchType.LAZY)
    private Set<FitUser> fitUsers = new HashSet<>();

    public GroupTraining() {
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
    public Set<FitUser> getUsers() {
        return fitUsers;
    }
    @JsonIgnore
    public void setUsers(Set<FitUser> fitUsers) {
        this.fitUsers = fitUsers;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }
}
