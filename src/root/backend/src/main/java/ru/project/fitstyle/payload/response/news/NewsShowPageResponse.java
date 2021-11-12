package ru.project.fitstyle.payload.response.news;

import ru.project.fitstyle.models.news.News;

import java.util.List;

public class NewsShowPageResponse {
    List<News> news;

    public NewsShowPageResponse(List<News> news) {
        this.news = news;
    }

    public NewsShowPageResponse() {
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
