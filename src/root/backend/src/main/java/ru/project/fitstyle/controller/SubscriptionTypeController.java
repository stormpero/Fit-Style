package ru.project.fitstyle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.exception.subscriptionType.ESubscriptionTypeError;
import ru.project.fitstyle.exception.subscriptionType.SubscriptionTypeException;
import ru.project.fitstyle.model.subscription.SubscriptionType;
import ru.project.fitstyle.payload.response.subscriptiontype.SubscriptionTypeResponse;
import ru.project.fitstyle.repository.SubscriptionTypeRepository;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subscriptiontype")
@PreAuthorize("hasRole('MODERATOR')")
public class SubscriptionTypeController {

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionTypeController(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    @GetMapping()
    public ResponseEntity<SubscriptionTypeResponse> show() {
        List<SubscriptionType> subscriptionTypes = subscriptionTypeRepository.findAll();
        if(subscriptionTypes.size() != 0) {
            return ResponseEntity.ok(new SubscriptionTypeResponse(subscriptionTypes));
        }
        else {
            throw new SubscriptionTypeException(ESubscriptionTypeError.NOTHING);
        }
    }
}
