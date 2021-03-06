package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.NewsProperties;
import ru.project.fitstyle.model.dto.news.NewsDto;
import ru.project.fitstyle.model.entity.news.News;
import ru.project.fitstyle.model.repository.NewsRepository;
import ru.project.fitstyle.service.NewsService;
import ru.project.fitstyle.service.exception.news.NewsPageNotFoundException;
import ru.project.fitstyle.service.exception.news.NewsStoryNotFoundException;

import java.util.List;

@Service
public class FitNewsService implements NewsService {

    private final NewsRepository newsRepository;

    private final int pageNumber;

    @Autowired
    public FitNewsService(final NewsRepository newsRepository, final NewsProperties newsProperties) {
        this.newsRepository = newsRepository;
        this.pageNumber = newsProperties.getPageNumber();
    }

    @Override
    public List<NewsDto> getNewsPage(final int number) {
        return newsRepository
                .findNewsPage(PageRequest.of(number - 1, pageNumber, Sort.by(Sort.Direction.DESC, "dateTime")))
                .filter(list -> list.size() != 0)
                .orElseThrow(() -> new NewsPageNotFoundException("There are no news on that page!"));
    }

    @Override
    public News getNewsById(final Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() ->
                        new NewsStoryNotFoundException("News with that id cannot be found!"));
    }

    @Override
    public void save(final News news) {
        newsRepository.save(news);
    }

    @Override
    public void delete(final News news) {
        newsRepository.delete(news);
    }
}
