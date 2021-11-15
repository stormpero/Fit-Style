package ru.project.fitstyle.payload.response.news;

import ru.project.fitstyle.models.news.News;

import java.sql.Timestamp;

public class NewsShowResponse {

    private final String header;

    private final String content;

    private final Timestamp dateTime;

    private final String imgURL;

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

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public String getImgURL() {
        return imgURL;
    }
}
