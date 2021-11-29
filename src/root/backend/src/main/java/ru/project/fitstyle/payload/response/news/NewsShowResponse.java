package ru.project.fitstyle.payload.response.news;

import ru.project.fitstyle.model.dto.news.News;

import java.sql.Timestamp;
import java.util.Date;

public class NewsShowResponse {

    private final String header;

    private final String content;

    private final Date dateTime;

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

    public Date getDateTime() {
        return dateTime;
    }

    public String getImgURL() {
        return imgURL;
    }
}
