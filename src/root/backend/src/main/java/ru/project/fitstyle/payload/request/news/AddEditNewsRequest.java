package ru.project.fitstyle.payload.request.news;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class AddEditNewsRequest {
    @NotBlank(message = "header should not be blank")
    @Size(min = 3, max = 50, message = "name should be more or equal than 3 and less or equal than 50 characters")
    private String header;

    @NotBlank(message = "content should not be blank")
    @Size(max = 1500, message = "content size should be less or equal then 500 chars")
    private String content;

    private Timestamp dateTime;

    @NotBlank(message = "imgURL should not be blank")
    @Size(min=1, max = 100, message = "imgURL should be more or equal than 1 and less or equal than 100 characters")
    private String imgURL;

    public AddEditNewsRequest(String header, String content, Timestamp dateTime, String imgURL) {
        this.header = header;
        this.content = content;
        this.dateTime = dateTime;
        this.imgURL = imgURL;
    }

    public AddEditNewsRequest() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
