package ru.project.fitstyle.controller.response.coach;

import ru.project.fitstyle.model.dto.user.FitUserFullNameDto;

import java.util.List;

public class AllCoachesResponse {
    public final List<FitUserFullNameDto> coaches;

    public AllCoachesResponse(List<FitUserFullNameDto> coaches) {
        this.coaches = coaches;
    }

    public List<FitUserFullNameDto> getCoaches() {
        return coaches;
    }
}
