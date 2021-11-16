package ru.project.fitstyle.payload.response.news;

import ru.project.fitstyle.models.news.News;

import java.util.List;

public class NewsShowPageResponse {
    private final List<News> news;

    public NewsShowPageResponse(List<News> news) {
        this.news = news;
    }

    public List<News> getNews() {
        return news;
    }
}
