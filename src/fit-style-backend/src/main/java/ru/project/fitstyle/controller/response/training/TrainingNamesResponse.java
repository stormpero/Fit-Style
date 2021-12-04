package ru.project.fitstyle.controller.response.training;


import ru.project.fitstyle.model.dto.training.TrainingNameDto;

import java.util.List;

public class TrainingNamesResponse {
    private final List<TrainingNameDto> trainingNames;

    public TrainingNamesResponse(List<TrainingNameDto> trainingNames) {
        this.trainingNames = trainingNames;
    }

    public List<TrainingNameDto> getTrainingNames() {
        return trainingNames;
    }
}
