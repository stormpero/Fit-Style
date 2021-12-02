package ru.project.fitstyle.service;

import ru.project.fitstyle.controller.response.news.NewsInfo;
import ru.project.fitstyle.model.entity.news.News;

import java.util.List;

public interface NewsService {

    List<NewsInfo> getNewsPage(int number);

    News getNewsById(Long id);

    void save(News news);

    void delete(News news);
}
