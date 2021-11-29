package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.exception.subscriptionType.ESubscriptionTypeError;
import ru.project.fitstyle.exception.subscriptionType.SubscriptionTypeException;
import ru.project.fitstyle.model.dto.subscription.SubscriptionType;
import ru.project.fitstyle.payload.response.subscriptiontype.SubscriptionTypeResponse;
import ru.project.fitstyle.service.subscription.SubscriptionTypeService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subscriptiontype")
@PreAuthorize("hasRole('MODERATOR')")
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;

    public SubscriptionTypeController(@Qualifier("fitSubscriptionTypeService") SubscriptionTypeService subscriptionTypeService) {
        this.subscriptionTypeService = subscriptionTypeService;
    }

    @GetMapping()
    public ResponseEntity<SubscriptionTypeResponse> show() {
        List<SubscriptionType> subscriptionTypes = subscriptionTypeService.getAllSubscriptionTypes();
        if(subscriptionTypes.size() != 0) {
            return ResponseEntity.ok(new SubscriptionTypeResponse(subscriptionTypes));
        }
        else {
            throw new SubscriptionTypeException(ESubscriptionTypeError.NOTHING);
        }
    }
}
