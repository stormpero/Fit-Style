package ru.project.fitstyle.controller.response.coach;

import ru.project.fitstyle.model.dto.user.CoachInfo;

import java.util.List;

public class AllCoachesResponse {
    public final List<CoachInfo> coaches;

    public AllCoachesResponse(List<CoachInfo> coaches) {
        this.coaches = coaches;
    }

    public List<CoachInfo> getCoaches() {
        return coaches;
    }
}
