package ru.project.fitstyle.controller.response.training;


import ru.project.fitstyle.model.dto.training.TrainingTypeDto;

import java.util.List;

public class TrainingNamesResponse {
    private final List<TrainingTypeDto> trainingNames;

    public TrainingNamesResponse(List<TrainingTypeDto> trainingNames) {
        this.trainingNames = trainingNames;
    }

    public List<TrainingTypeDto> getTrainingNames() {
        return trainingNames;
    }
}
