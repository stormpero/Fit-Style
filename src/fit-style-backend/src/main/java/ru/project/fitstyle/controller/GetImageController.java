package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.service.AuthService;
import ru.project.fitstyle.service.NewsService;
import ru.project.fitstyle.service.StorageService;
import ru.project.fitstyle.service.UserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/get-image")
@PreAuthorize("hasRole('USER')")
public class GetImageController {

    private final AuthService authService;

    private final StorageService imageStorageService;

    private final UserService userService;

    private final NewsService newsService;

    @Autowired
    public GetImageController(UserService userService, NewsService newsService,
                              AuthService authService, StorageService imageStorageService) {
        this.userService = userService;
        this.newsService = newsService;
        this.authService = authService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping("/user")
    public ResponseEntity<Resource> getUserProfileImage() {
        final Resource resource = imageStorageService.loadAsResource(userService.getUserByEmail(authService.getEmail()).getImgURL());

        return getImageResponse(resource);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<Resource> getNewsImage(@PathVariable("id") Long id) {
        final Resource resource = imageStorageService.loadAsResource(newsService.getNewsById(id).getImgURL());

        return getImageResponse(resource);
    }

    private ResponseEntity<Resource> getImageResponse(Resource resource) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
