package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.project.fitstyle.controller.request.auth.SignupRequest;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.controller.response.fitusers.AllFitUserResponse;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.service.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final PasswordEncoder encoder;

    private final UserService userService;

    private final RoleService roleService;

    private final SubscriptionTypeService subscriptionTypeService;

    private final StorageService imageStorageService;

    @Autowired
    public UsersController(final PasswordEncoder encoder,
                           final UserService userService,
                           final RoleService roleService,
                           final SubscriptionTypeService subscriptionTypeService,
                           final StorageService imageStorageService) {
        this.encoder = encoder;
        this.userService = userService;
        this.roleService = roleService;
        this.subscriptionTypeService = subscriptionTypeService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<AllFitUserResponse> getAllFitUsers() {
        return ResponseEntity.ok(
                new AllFitUserResponse(userService.getAllUsers())
        );
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> add(@Valid @RequestPart(value = "request") final SignupRequest request,
                                                       @RequestPart(value = "image", required = false) final MultipartFile image) {
        userService.validateEmail(request.getEmail());

        FitUser fitUser = createFitUser(request);

        fitUser.setImgURL(imageStorageService.store(image));

        userService.saveUser(fitUser, roleService.createRoles(request.getRoles()),
                subscriptionTypeService.createFitUserSubscription(request.getSubscriptionTypeInfo().getSubscriptionTypeId(),
                        request.getSubscriptionTypeInfo().getContractNumber()));

        return ResponseEntity.ok(
                new SuccessMessage("User registered successfully!"));
    }

    private FitUser createFitUser(final SignupRequest request) {
        return new FitUser(request.getName(), request.getSurname(),
                request.getPatronymic(), request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getAge(), request.getGender(),
                request.getBirthdate(), request.getTelephone(),
                request.getPassport(), request.getAddress());
    }
}
