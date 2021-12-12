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

    /**
     * Get user profile image
     * */
    @GetMapping("/user")
    public ResponseEntity<Resource> getUserProfileImage() {
        return createImageResponse(imageStorageService.loadAsResource(userService.getUserByEmail(authService.getEmail()).getImgURL()));
    }

    /**
     * Get user profile image by its id
     * */
    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/user/{id}")
    public ResponseEntity<Resource> getUserProfileImageById(@PathVariable("id") Long id) {
        return createImageResponse(imageStorageService.loadAsResource(userService.getUserById(id).getImgURL()));
    }


    /**
     * Get news image by its id
     * */
    @GetMapping("/news/{id}")
    public ResponseEntity<Resource> getNewsImage(@PathVariable("id") Long id) {
        return createImageResponse(imageStorageService.loadAsResource(newsService.getNewsById(id).getImgURL()));
    }

    private ResponseEntity<Resource> createImageResponse(Resource resource) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
