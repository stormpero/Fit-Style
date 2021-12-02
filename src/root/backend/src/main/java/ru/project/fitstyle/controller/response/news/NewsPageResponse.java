package ru.project.fitstyle.controller.response.news;

import ru.project.fitstyle.model.dto.news.NewsInfo;

import java.util.List;

public class NewsPageResponse {
    private final List<NewsInfo> news;

    public NewsPageResponse(List<NewsInfo> news) {
        this.news = news;
    }

    public List<NewsInfo> getNews() {
        return news;
    }
}
