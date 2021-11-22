package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.exception.newspage.ENewsPageError;
import ru.project.fitstyle.exception.newspage.NewsPageException;
import ru.project.fitstyle.exception.newsstory.ENewsStoryError;
import ru.project.fitstyle.exception.newsstory.NewsStoryException;
import ru.project.fitstyle.model.news.News;
import ru.project.fitstyle.payload.request.news.AddEditNewsRequest;
import ru.project.fitstyle.payload.response.news.NewsInfoResponse;
import ru.project.fitstyle.payload.response.news.NewsShowPageResponse;
import ru.project.fitstyle.payload.response.news.NewsShowResponse;
import ru.project.fitstyle.payload.responsemessage.SuccessMessage;
import ru.project.fitstyle.repository.NewsRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/news")
@PreAuthorize("hasRole('USER')")
public class NewsController {
    private final NewsRepository newsRepository;

    //Constant variable that specifies number of news in one page
    final int numberOfNewsInOnePage = 6;

    @Autowired
    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/{page_number}")
    public ResponseEntity<NewsShowPageResponse> showPage(@PathVariable("page_number") int pageNumber) {
        //Here we get first 6 (can be specified) recently added news

        if(pageNumber > 0) {
            List<News> news =
                    newsRepository
                            .findAll(PageRequest.of(pageNumber - 1, numberOfNewsInOnePage,
                    Sort.by(Sort.Direction.DESC, "dateTime"))).toList();

            if (news.size() != 0) {
                return ResponseEntity.ok(
                        new NewsShowPageResponse(news));
            } else {
                throw new NewsPageException(ENewsPageError.OVER);
            }
        }
        else {
            throw new NewsPageException(ENewsPageError.LESS_THAN_ZERO);
        }
    }

    @GetMapping("/story/{id}")
    public ResponseEntity<NewsShowResponse> show(@PathVariable("id") Long id) {
        //Find news by given id
        News news = newsRepository.findById(id)
                .orElseThrow(() ->
                        new NewsStoryException(ENewsStoryError.NOT_FOUND));
        return ResponseEntity.ok(new NewsShowResponse(news));
    }

    @PostMapping()
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> add(@Valid @RequestBody AddEditNewsRequest addEditNewsRequest) {
        //Add News
        News news = new News(
                addEditNewsRequest.getHeader(),
                addEditNewsRequest.getContent(),
                addEditNewsRequest.getDateTime(),
                addEditNewsRequest.getImgURL()
        );

        newsRepository.save(news);
        return ResponseEntity.ok(
                new SuccessMessage("Success! News created!")
        );
    }


    //TODO Make specific methods only to update separate fields without updating the whole object

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> update(@Valid @RequestBody AddEditNewsRequest addEditNewsRequest,
                                                 @PathVariable("id") Long id) {
    //Update news. It currently updates all fields of the DB object instead of updating only those which are changed
        News news = newsRepository.findById(id)
                .orElseThrow(() ->
                        new NewsStoryException(ENewsStoryError.NOT_FOUND));
        news.setHeader(addEditNewsRequest.getHeader());
        news.setContent(addEditNewsRequest.getContent());
        news.setDateTime(addEditNewsRequest.getDateTime());
        news.setImgURL(addEditNewsRequest.getImgURL());

        newsRepository.save(news);
        return ResponseEntity.ok(
                new SuccessMessage("Success! News updated!")
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> delete(@PathVariable("id") Long id) {
        //Delete news
        News news = newsRepository.findById(id)
                .orElseThrow(() ->
                        new NewsStoryException(ENewsStoryError.NOT_FOUND));
        newsRepository.delete(news);
        return ResponseEntity.ok(
                new SuccessMessage("Success! News deleted!")
        );
    }

    @GetMapping("/info")
    public ResponseEntity<NewsInfoResponse> getInfo() {
        long numberOfNews = newsRepository.count();
        long numberOfPages =
                (long)Math.ceil((double)numberOfNews/numberOfNewsInOnePage);
        NewsInfoResponse newsInfoResponse =
                new NewsInfoResponse(numberOfPages, numberOfNews, numberOfNewsInOnePage);
        return ResponseEntity.ok(newsInfoResponse);
    }
}
