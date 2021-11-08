package ru.project.fitstyle.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "trainingUser")
public class TrainingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(mappedBy = "trainingUser", fetch = FetchType.EAGER)
    private Collection<Training> owner;

    public TrainingUser() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection<Training> getOwner() {
        return owner;
    }

    public void setOwner(Collection<Training> owner) {
        this.owner = owner;
    }
}