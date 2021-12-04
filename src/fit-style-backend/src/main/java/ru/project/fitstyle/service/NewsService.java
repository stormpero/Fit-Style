package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.news.NewsDto;
import ru.project.fitstyle.model.entity.news.News;

import java.util.List;

public interface NewsService {

    List<NewsDto> getNewsPage(final int number);

    News getNewsById(final Long id);

    void save(final News news);

    void delete(final News news);
}
