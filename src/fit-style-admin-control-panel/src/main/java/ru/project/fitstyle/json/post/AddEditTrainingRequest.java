package ru.project.fitstyle.json.post;

public class AddEditTrainingRequest {

    private String name;

    public AddEditTrainingRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
