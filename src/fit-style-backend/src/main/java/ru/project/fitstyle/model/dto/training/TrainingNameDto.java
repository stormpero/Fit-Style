package ru.project.fitstyle.model.dto.training;

public class TrainingNameDto {
    private final Long id;

    private final String name;

    public TrainingNameDto(final Long id, final String name) {
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
