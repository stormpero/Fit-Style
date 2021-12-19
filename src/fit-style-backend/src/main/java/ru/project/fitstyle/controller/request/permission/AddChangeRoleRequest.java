package ru.project.fitstyle.controller.request.permission;

public class AddChangeRoleRequest {
    private String name;

    public AddChangeRoleRequest(String name) {
        this.name = name;
    }

    public AddChangeRoleRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
