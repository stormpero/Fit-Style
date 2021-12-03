package ru.project.fitstyle.controller.response.training;


import ru.project.fitstyle.model.dto.training.TrainingNameInfo;

import java.util.List;

public class TrainingNamesResponse {
    private final List<TrainingNameInfo> trainingName;

    public TrainingNamesResponse(List<TrainingNameInfo> trainingName) {
        this.trainingName = trainingName;
    }

    public List<TrainingNameInfo> getTrainingName() {
        return trainingName;
    }
}
