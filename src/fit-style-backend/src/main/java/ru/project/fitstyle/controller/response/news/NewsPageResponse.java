package ru.project.fitstyle.controller.response.news;

import ru.project.fitstyle.model.dto.news.NewsDto;

import java.util.List;

public class NewsPageResponse {
    private final List<NewsDto> news;

    public NewsPageResponse(List<NewsDto> news) {
        this.news = news;
    }

    public List<NewsDto> getNews() {
        return news;
    }
}
