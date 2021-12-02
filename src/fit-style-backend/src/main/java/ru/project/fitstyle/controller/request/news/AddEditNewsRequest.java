package ru.project.fitstyle.controller.request.news;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class AddEditNewsRequest {
    @NotBlank(message = "header should not be blank")
    @Size(min = 3, max = 50, message = "name should be more or equal than 3 and less or equal than 50 characters")
    private String header;

    @NotBlank(message = "content should not be blank")
    @Size(max = 1500, message = "content size should be less or equal then 500 chars")
    private String content;

    private Date dateTime;

    public AddEditNewsRequest(String header, String content, Date dateTime) {
        this.header = header;
        this.content = content;
        this.dateTime = dateTime;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
