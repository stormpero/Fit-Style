package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.project.fitstyle.controller.request.user.*;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.controller.response.user.AllUsersResponse;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.service.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final PasswordEncoder encoder;

    private final UserService userService;

    private final RoleService roleService;

    private final SubscriptionTypeService subscriptionTypeService;

    private final StorageService imageStorageService;

    private final PasswordRecoveryService passwordRecoveryService;

    private final AuthService authService;

    @Autowired
    public UserController(PasswordEncoder encoder, UserService userService, RoleService roleService,
                          SubscriptionTypeService subscriptionTypeService, StorageService imageStorageService,
                          PasswordRecoveryService passwordRecoveryService, AuthService authService) {
        this.encoder = encoder;
        this.userService = userService;
        this.roleService = roleService;
        this.subscriptionTypeService = subscriptionTypeService;
        this.imageStorageService = imageStorageService;
        this.passwordRecoveryService = passwordRecoveryService;
        this.authService = authService;
    }

    /**
     * Get all users
     * */
    @GetMapping("/all")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<AllUsersResponse> getAllFitUsers() {
        return ResponseEntity.ok(
                new AllUsersResponse(userService.getAllUsers())
        );
    }

    /**
     * Add new user
     * */
    @PostMapping("/add")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> add(@Valid @RequestPart(value = "request") final AddEditUserRequest request,
                                                       @RequestPart(value = "image", required = false) final MultipartFile image) {
        userService.validateEmailForRegister(request.getEmail());

        FitUser fitUser = createFitUser(request);

        fitUser.setImgURL(imageStorageService.store(image));

        userService.saveUser(fitUser, roleService.createRoles(request.getRoles()),
                subscriptionTypeService.createFitUserSubscription(request.getSubscriptionTypeInfo().getSubscriptionTypeId(),
                        request.getSubscriptionTypeInfo().getContractNumber()));

        return ResponseEntity.ok(
                new SuccessMessage("User registered successfully!"));
    }

    /**
     * Update user info (currently not used)
     * */
    @PatchMapping("/update/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> update(@Valid @RequestPart(value = "request") final AddEditUserRequest request,
                                                 @RequestPart(value = "image", required = false) final MultipartFile image,
                                                 @PathVariable("id") final Long id) {
        userService.validateEmailForRegister(request.getEmail());

        FitUser fitUser = userService.getUserById(id);

        fitUser.setImgURL(imageStorageService.store(image));

        userService.saveUser(fitUser, roleService.createRoles(request.getRoles()),
                subscriptionTypeService.createFitUserSubscription(request.getSubscriptionTypeInfo().getSubscriptionTypeId(),
                        request.getSubscriptionTypeInfo().getContractNumber()));

        return ResponseEntity.ok(
                new SuccessMessage("User updated successfully!"));
    }

    /**
     * Change user password by moderator
     * */
    @PostMapping("/change-password/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordRequest request) {
        userService.changePassword(id, encoder.encode(request.getPassword()));

        return ResponseEntity.ok(
                new SuccessMessage("Password changed successfully!"));
    }

    /**
     * Disable user
     * */
    @GetMapping("/disable/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> disable(@PathVariable("id") Long id) {
        userService.disableUser(id);
        return ResponseEntity.ok(
                new SuccessMessage("User disabled successfully!"));
    }

    /**
     * Enable user
     * */
    @GetMapping("/enable/{id}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> enable(@PathVariable("id") Long id) {
        userService.enableUser(id);
        return ResponseEntity.ok(
                new SuccessMessage("User enabled successfully!"));
    }

    /**
     * Send recovery email to provided email if user with that email exists in database
     * */
    @PostMapping("/ask-for-recover-with-email")
    public ResponseEntity<SuccessMessage> askForRecoverWithEmail(@RequestBody AskForRecoverRequest request) {
        passwordRecoveryService.sendEmail(request.getEmail());
        return ResponseEntity.ok(
                new SuccessMessage("The message was sent to the given address!"));
    }

    /**
     * Send recovery email to user email. Used to change password of currently logged user, currently not used
     * */
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ask-for-recover")
    public ResponseEntity<SuccessMessage> askForRecover() {

        passwordRecoveryService.sendEmail(authService.getEmail());

        return ResponseEntity.ok(
                new SuccessMessage("The message was sent to the given address!"));
    }

    /**
     * Check if provided recovery code fits with one that stored in database
     * */
    @PostMapping("/confirm-recovery")
    public ResponseEntity<SuccessMessage> confirmRecovery(@RequestBody ConfirmRecoveryRequest request) {
        passwordRecoveryService.confirmRecovery(request.getCode(), encoder.encode(request.getPassword()));

        return ResponseEntity.ok(
                new SuccessMessage("Password changed successfully!"));
    }


    @PostMapping("/assign-role")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> roleAssign(@RequestBody AssignRoleRequest request) {
        userService.roleAssign(request.getUserId(), request.getRoleId());

        return ResponseEntity.ok(
                new SuccessMessage("Role assigned successfully!"));
    }

    /**
     * Help method. Used to create user
     * */
    private FitUser createFitUser(final AddEditUserRequest request) {
        return new FitUser(request.getName(), request.getSurname(),
                request.getPatronymic(), request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getAge(), request.getGender(),
                request.getBirthdate(), request.getTelephone(),
                request.getPassport(), request.getAddress());
    }
}
