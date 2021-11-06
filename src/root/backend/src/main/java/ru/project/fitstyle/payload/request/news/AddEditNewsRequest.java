package ru.project.fitstyle.payload.request.news;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class AddEditNewsRequest {
    @NotBlank
    @Size(max = 50)
    private String header;

    @NotBlank
    @Size(max = 500)
    private String content;

    private Timestamp dateTime;

    @NotBlank
    @Size(max = 50)
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
