package ru.project.fitstyle.controller.response.profile;

import java.util.List;

public class AllCoachesInfoResponse {
    public final List<CoachInfo> coaches;

    public AllCoachesInfoResponse(List<CoachInfo> coaches) {
        this.coaches = coaches;
    }

    public List<CoachInfo> getCoaches() {
        return coaches;
    }
}
