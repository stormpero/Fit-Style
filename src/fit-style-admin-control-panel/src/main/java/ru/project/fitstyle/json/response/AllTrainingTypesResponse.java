package ru.project.fitstyle.json.response;

import java.util.List;

public class AllTrainingTypesResponse {
    private List<TrainingTypeDto> trainingNames;

    public AllTrainingTypesResponse(List<TrainingTypeDto> trainingNames) {
        this.trainingNames = trainingNames;
    }

    public AllTrainingTypesResponse() {
    }

    public List<TrainingTypeDto> getTrainingNames() {
        return trainingNames;
    }


    public static class TrainingTypeDto {
        private Long id;

        private String name;

        public TrainingTypeDto(final Long id, final String name) {
            this.id = id;
            this.name = name;
        }

        public TrainingTypeDto() {
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}