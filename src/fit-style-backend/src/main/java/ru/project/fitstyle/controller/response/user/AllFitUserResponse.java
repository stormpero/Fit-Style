package ru.project.fitstyle.controller.response.user;

import ru.project.fitstyle.model.dto.user.FitUserDto;

import java.util.List;

public class AllFitUserResponse {
    private final List<FitUserDto> fitUsers;

    public AllFitUserResponse(List<FitUserDto> fitUsers) {
        this.fitUsers = fitUsers;
    }

    public List<FitUserDto> getFitUsers() {
        return fitUsers;
    }
}
