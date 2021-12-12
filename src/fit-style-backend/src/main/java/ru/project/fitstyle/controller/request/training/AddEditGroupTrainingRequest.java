package ru.project.fitstyle.controller.request.training;

import java.util.Date;

public class AddEditGroupTrainingRequest {

    private Long trainingId;

    private Date date;

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
