package ru.project.fitstyle.controller.response.user;

import ru.project.fitstyle.model.dto.user.FitUserFullInfoDto;

import java.util.List;

public class AllUsersResponse {
    private final List<FitUserFullInfoDto> fitUsers;

    public AllUsersResponse(List<FitUserFullInfoDto> fitUsers) {
        this.fitUsers = fitUsers;
    }

    public List<FitUserFullInfoDto> getFitUsers() {
        return fitUsers;
    }
}
