package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.project.fitstyle.exception.newspage.ENewsPageError;
import ru.project.fitstyle.exception.newspage.NewsPageException;
import ru.project.fitstyle.model.news.News;
import ru.project.fitstyle.payload.request.news.AddEditNewsRequest;
import ru.project.fitstyle.payload.response.news.NewsShowPageResponse;
import ru.project.fitstyle.payload.responsemessage.SuccessMessage;
import ru.project.fitstyle.service.news.NewsService;
import ru.project.fitstyle.service.storage.StorageService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/news")
@PreAuthorize("hasRole('USER')")
public class NewsController {
    private final NewsService newsService;
    private final StorageService storageService;

    @Autowired
    public NewsController(@Qualifier("fitNewsService") NewsService newsService,
                          @Qualifier("imageStorageService") StorageService storageService) {
        this.newsService = newsService;
        this.storageService = storageService;
    }

    @GetMapping("/{page_number}")
    public ResponseEntity<NewsShowPageResponse> showPage(@PathVariable("page_number") int pageNumber) {
        //Here we get first 6 (can be specified) recently added news
        List<News> news = newsService.getNewsPage(pageNumber);

        if (news.size() != 0) {
            return ResponseEntity.ok(
                    new NewsShowPageResponse(news));
        } else {
            throw new NewsPageException(ENewsPageError.OVER);
        }
    }

    @PostMapping()
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> add(@Valid @RequestPart(value = "request") AddEditNewsRequest request,
                                              @RequestPart(value = "image", required = false) MultipartFile image) {
        //Add News
        News news = new News(
                request.getHeader(),
                request.getContent(),
                request.getDateTime(),
                image.getOriginalFilename()
        );

        storageService.store(image);
        newsService.save(news);

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
        News news = newsService.getNewsById(id);
        news.setHeader(addEditNewsRequest.getHeader());
        news.setContent(addEditNewsRequest.getContent());
        news.setDateTime(addEditNewsRequest.getDateTime());
        news.setImgURL(addEditNewsRequest.getImgURL());

        newsService.save(news);
        return ResponseEntity.ok(
                new SuccessMessage("Success! News updated!")
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> delete(@PathVariable("id") Long id) {
        //Delete news
        News news = newsService.getNewsById(id);
        newsService.delete(news);
        return ResponseEntity.ok(
                new SuccessMessage("Success! News deleted!")
        );
    }
}
