package ru.project.fitstyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.models.News;
import ru.project.fitstyle.payload.request.news.AddEditNewsRequest;
import ru.project.fitstyle.payload.response.news.NewsShowPageResponse;
import ru.project.fitstyle.payload.response.news.NewsShowResponse;
import ru.project.fitstyle.payload.response.utils.MessageResponse;
import ru.project.fitstyle.repository.NewsRepository;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/news")
@PreAuthorize("hasRole('USER')")
public class NewsController {
    //Constant variable that specifies number of news in one page
    private final int newsInPage = 6;
    private final NewsRepository newsRepository;

    @Autowired
    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/{page_number}")
    public ResponseEntity<?> showPage(@PathVariable("page_number") int pageNumber)
    {
        //Here we get first 6 (can be specified) recently added news
        List<News> news = newsRepository.findAll(PageRequest.of(pageNumber - 1, newsInPage,
                Sort.by(Sort.Direction.DESC, "dateTime"))).toList();

        if(news.size() != 0)
        {
            return ResponseEntity.ok(new NewsShowPageResponse(news));
        }
        else
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Oops..."));
        }
    }

    @GetMapping("/story/{id}")
    public ResponseEntity<?> show(@PathVariable("id") Long id)
    {
        //Find news by given id
        News news = newsRepository.findById(id).orElse(null);
        if(news != null)
        {
            return ResponseEntity.ok(new NewsShowResponse(news));
        }
        else
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("News with that id has been deleted or never been created!"));
        }
    }

    @PostMapping()
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> add(@Valid @RequestBody AddEditNewsRequest addEditNewsRequest,
                         BindingResult bindingResult)
    {
        //Add News
        if(bindingResult.hasErrors())
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error! Wrong addEditNewsRequest json"));
        }
        News news = new News(
                addEditNewsRequest.getHeader(),
                addEditNewsRequest.getContent(),
                addEditNewsRequest.getDateTime(),
                addEditNewsRequest.getImgURL()
        );

        newsRepository.save(news);
        return ResponseEntity.ok(
                new MessageResponse("Success! News created!")
        );
    }


    //TODO Make specific methods only to update separate fields without updating the whole object

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> update(@Valid @RequestBody AddEditNewsRequest addEditNewsRequest,
                         BindingResult bindingResult,
                         @PathVariable("id") Long id)
    {
        //Update news. It currently updates all fields of the DB object instead of updating only those which are changed
        if(bindingResult.hasErrors())
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error! Wrong addEditNewsRequest json"));
        }
        News news = newsRepository.findById(id).orElse(null);
        if(news != null)
        {
            news.setHeader(addEditNewsRequest.getHeader());
            news.setContent(addEditNewsRequest.getContent());
            news.setDateTime(addEditNewsRequest.getDateTime());
            news.setImgURL(addEditNewsRequest.getImgURL());

            newsRepository.save(news);
            return ResponseEntity.ok(
                    new MessageResponse("Success! News updated!")
            );
        }
        else
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("News with that id has been deleted or never been created!"));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        //Delete news
        News news = newsRepository.findById(id).orElse(null);
        if(news != null)
        {
            newsRepository.delete(news);
            return ResponseEntity.ok(
                    new MessageResponse("Success! News deleted!")
            );
        }
        else
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("News with that id has been deleted or never been created!"));
        }
    }
}
