package ru.project.fitstyle.controller.response.news;

import java.util.List;

public class NewsShowPageResponse {
    private final List<NewsInfo> news;

    public NewsShowPageResponse(List<NewsInfo> news) {
        this.news = news;
    }

    public List<NewsInfo> getNews() {
        return news;
    }
}
