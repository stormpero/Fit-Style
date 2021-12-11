package ru.project.fitstyle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.response.subscription.SubscriptionTypeResponse;
import ru.project.fitstyle.service.SubscriptionTypeService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subscription-type")
@PreAuthorize("hasRole('MODERATOR')")
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;

    public SubscriptionTypeController(final SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @GetMapping()
    public ResponseEntity<SubscriptionTypeResponse> show() {
        return ResponseEntity.ok(new SubscriptionTypeResponse(subscriptionTypeService.getAllSubscriptionTypes()));
    }
}
