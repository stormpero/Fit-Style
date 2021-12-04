package ru.project.fitstyle.model.dto.training;

public class TrainingNameDto {
    private final Long id;

    private final String name;

    public TrainingNameDto(Long id, String name) {
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
