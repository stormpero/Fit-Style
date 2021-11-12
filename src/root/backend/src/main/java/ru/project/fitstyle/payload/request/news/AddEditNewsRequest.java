package ru.project.fitstyle.payload.request.news;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class AddEditNewsRequest {
    @NotBlank(message = "header should not be blank")
    @Size(max = 50, message = "header size should be less or equal than 50 chars")
    private String header;

    @NotBlank(message = "content should not be blank")
    @Size(max = 500, message = "content size should be less or equal then 500 chars")
    private String content;

    private Timestamp dateTime;

    @NotBlank(message = "imgUrl should not be blank")
    @Size(max = 100, message = "imgUrl should be less or equal than 100 chars")
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
