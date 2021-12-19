package ru.project.fitstyle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.request.subscription.AddSubscriptionTypeRequest;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.controller.response.subscription.SubscriptionTypeResponse;
import ru.project.fitstyle.model.entity.subscription.SubscriptionType;
import ru.project.fitstyle.service.SubscriptionTypeService;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/subscription-type")
@PreAuthorize("hasRole('MODERATOR')")
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;

    public SubscriptionTypeController(final SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }

    /**
     * Return all subscription types
     * */
    @GetMapping()
    public ResponseEntity<SubscriptionTypeResponse> getAllSubscriptionTypes() {
        return ResponseEntity.ok(new SubscriptionTypeResponse(subscriptionTypeService.getAllSubscriptionTypes()));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PostMapping()
    public ResponseEntity<SuccessMessage> add(@RequestBody AddSubscriptionTypeRequest request) {
        SubscriptionType subscriptionType =
                new SubscriptionType(request.getName(), request.getValidityMonths(), request.getPlacementTime(), request.getCost());
        subscriptionTypeService.save(subscriptionType);
        return ResponseEntity.ok(new SuccessMessage("Subscription type successfully added!"));
    }
}
