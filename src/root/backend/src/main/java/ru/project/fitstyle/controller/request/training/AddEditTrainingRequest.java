package ru.project.fitstyle.controller.request.training;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddEditTrainingRequest {

    @NotBlank(message = "name should not be blank")
    @Size(min=3, max=50, message = "name should be more or equal than 3 and less or equal than 50 characters")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
