package ru.project.fitstyle.model.dto.training;

public class TrainingTypeDto {
    private final Long id;

    private final String name;

    public TrainingTypeDto(final Long id, final String name) {
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
