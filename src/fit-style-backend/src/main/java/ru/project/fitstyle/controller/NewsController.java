package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.model.entity.news.News;
import ru.project.fitstyle.controller.request.news.AddEditNewsRequest;
import ru.project.fitstyle.controller.response.news.NewsPageResponse;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.service.NewsService;
import ru.project.fitstyle.service.StorageService;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
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
    public ResponseEntity<NewsPageResponse> showPage(@PathVariable("page_number") int pageNumber) {
        //Here we get first 6 (can be specified) recently added news
        return ResponseEntity.ok(new NewsPageResponse(newsService.getNewsPage(pageNumber)));
    }

    @PostMapping()
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> add(@Valid @RequestBody AddEditNewsRequest request
                                              /*@RequestPart(value = "image", required = false) MultipartFile image*/) {
        //Add News
        News news = new News(
                request.getHeader(),
                request.getContent(),
                request.getDateTime(),
                //image.getOriginalFilename()
                "testfilename"
        );

        //storageService.store(image);
        newsService.save(news);

        return ResponseEntity.ok(
                new SuccessMessage("Success! News created!")
        );
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> update(@Valid @RequestBody AddEditNewsRequest addEditNewsRequest,
                                                 @PathVariable("id") Long id) {
    //Update news.
        News news = newsService.getNewsById(id);
        news.setHeader(addEditNewsRequest.getHeader());
        news.setContent(addEditNewsRequest.getContent());
        news.setDateTime(addEditNewsRequest.getDateTime());

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
