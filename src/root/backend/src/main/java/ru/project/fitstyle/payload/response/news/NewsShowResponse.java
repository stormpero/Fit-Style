package ru.project.fitstyle.payload.response.news;

import ru.project.fitstyle.models.news.News;

import java.sql.Timestamp;

public class NewsShowResponse {

    private String header;

    private String content;

    private Timestamp dateTime;

    private String imgURL;

    public NewsShowResponse(String header, String content, Timestamp dateTime, String imgURL) {
        this.header = header;
        this.content = content;
        this.dateTime = dateTime;
        this.imgURL = imgURL;
    }

    public NewsShowResponse(News news) {
        this.header = news.getHeader();
        this.content = news.getContent();
        this.dateTime = news.getDateTime();
        this.imgURL = news.getImgURL();
    }

    public NewsShowResponse() {
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
