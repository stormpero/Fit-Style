package ru.project.fitstyle.model.entity.training;

import javax.persistence.*;

@Entity
@Table(name = "training")
public class TrainingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",
            nullable = false, updatable = false, unique = true)
    private Long id;

    @Column(name = "name", length = 50,
            nullable = false, unique = true)
    private String name;

    public TrainingType() {
    }

    public TrainingType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
