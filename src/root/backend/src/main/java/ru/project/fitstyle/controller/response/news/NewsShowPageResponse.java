package ru.project.fitstyle.controller.response.news;

import ru.project.fitstyle.model.dto.news.News;

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
