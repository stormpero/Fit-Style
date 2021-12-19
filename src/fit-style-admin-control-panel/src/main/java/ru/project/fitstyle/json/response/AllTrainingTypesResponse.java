package ru.project.fitstyle.json.response;

import java.util.List;

public class AllTrainingTypesResponse {
    private final List<TrainingTypeDto> trainingNames;

    public AllTrainingTypesResponse(List<TrainingTypeDto> trainingNames) {
        this.trainingNames = trainingNames;
    }

    public List<TrainingTypeDto> getTrainingNames() {
        return trainingNames;
    }






    public static class TrainingTypeDto {
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
}