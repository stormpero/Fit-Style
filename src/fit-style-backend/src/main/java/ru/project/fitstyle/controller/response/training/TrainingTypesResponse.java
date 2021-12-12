package ru.project.fitstyle.controller.response.training;


import ru.project.fitstyle.model.dto.training.TrainingTypeDto;

import java.util.List;

public class TrainingTypesResponse {
    private final List<TrainingTypeDto> trainingNames;

    public TrainingTypesResponse(List<TrainingTypeDto> trainingNames) {
        this.trainingNames = trainingNames;
    }

    public List<TrainingTypeDto> getTrainingNames() {
        return trainingNames;
    }
}
