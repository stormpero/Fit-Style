package ru.project.fitstyle.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.config.properties.NewsProperties;
import ru.project.fitstyle.exception.newsstory.ENewsStoryError;
import ru.project.fitstyle.exception.newsstory.NewsStoryException;
import ru.project.fitstyle.model.news.News;
import ru.project.fitstyle.repository.NewsRepository;

import java.util.List;

@Service
public class FitNewsService implements NewsService {

    private final NewsRepository newsRepository;

    private final int pageNumber;

    @Autowired
    public FitNewsService(NewsRepository newsRepository, NewsProperties newsProperties) {
        this.newsRepository = newsRepository;
        this.pageNumber = newsProperties.getPageNumber();
    }

    @Override
    public List<News> getNewsPage(int number) {
        return newsRepository
                .findAll(PageRequest.of(number - 1, pageNumber,
                        Sort.by(Sort.Direction.DESC, "dateTime"))).toList();
    }

    @Override
    public News getNewsById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() ->
                        new NewsStoryException(ENewsStoryError.NOT_FOUND));
    }

    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public void delete(News news) {
        newsRepository.delete(news);
    }
}
