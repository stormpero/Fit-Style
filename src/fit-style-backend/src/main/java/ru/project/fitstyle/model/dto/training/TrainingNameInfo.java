package ru.project.fitstyle.model.dto.training;

public class TrainingNameInfo {
    private final Long id;

    private final String name;

    public TrainingNameInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
