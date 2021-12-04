package ru.project.fitstyle.model.dto.news;

import java.util.Date;

public class NewsDto {
    private final Long id;

    private final String header;

    private final String content;

    private final Date dateTime;

    private final String imgURL;

    public NewsDto(Long id, String header, String content, Date dateTime, String imgURL) {
        this.id = id;
        this.header = header;
        this.content = content;
        this.dateTime = dateTime;
        this.imgURL = imgURL;
    }

    public Long getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public String getImgURL() {
        return imgURL;
    }
}
