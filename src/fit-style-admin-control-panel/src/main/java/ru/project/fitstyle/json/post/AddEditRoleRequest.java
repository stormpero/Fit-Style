package ru.project.fitstyle.json.post;

public class AddEditRoleRequest {
    private String name;

    public AddEditRoleRequest(String name) {
        this.name = name;
    }

    public AddEditRoleRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
