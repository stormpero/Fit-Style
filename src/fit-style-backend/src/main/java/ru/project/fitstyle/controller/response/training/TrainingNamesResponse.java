package ru.project.fitstyle.controller.response.training;


import ru.project.fitstyle.model.dto.training.TrainingNameInfo;

import java.util.List;

public class TrainingNamesResponse {
    private final List<TrainingNameInfo> trainingNames;

    public TrainingNamesResponse(List<TrainingNameInfo> trainingNames) {
        this.trainingNames = trainingNames;
    }

    public List<TrainingNameInfo> getTrainingNames() {
        return trainingNames;
    }
}
