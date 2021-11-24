package ru.project.fitstyle.service.news;

import ru.project.fitstyle.model.news.News;

import java.util.List;

public interface NewsService {

    List<News> getNewsPage(int number);

    News getNewsById(Long id);

    void save(News news);

    void delete(News news);

    long getCount();
}
