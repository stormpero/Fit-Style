package ru.project.fitstyle.controller.response.coach;

import ru.project.fitstyle.model.dto.user.CoachDto;

import java.util.List;

public class AllCoachesResponse {
    public final List<CoachDto> coaches;

    public AllCoachesResponse(List<CoachDto> coaches) {
        this.coaches = coaches;
    }

    public List<CoachDto> getCoaches() {
        return coaches;
    }
}
