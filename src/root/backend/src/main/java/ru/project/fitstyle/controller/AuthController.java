package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;
import ru.project.fitstyle.model.entity.user.RefreshToken;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.controller.request.auth.LoginRequest;
import ru.project.fitstyle.controller.request.auth.SignupRequest;
import ru.project.fitstyle.controller.response.auth.LoginResponse;
import ru.project.fitstyle.controller.response.auth.RefreshTokenResponse;
import ru.project.fitstyle.controller.response.other.SuccessMessage;
import ru.project.fitstyle.service.*;
import ru.project.fitstyle.service.impl.details.FitUserDetails;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final UserService userService;

    private final RoleService roleService;

    private final SubscriptionTypeService subscriptionTypeService;

    private final TokenService accessTokenService;

    private final TokenService refreshTokenService;

    private final CookieService refreshTokenCookieService;

    private final AuthService authService;

    private final StorageService imageStorageService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,PasswordEncoder encoder,
                          @Qualifier("fitUserService") UserService userService,
                          @Qualifier("fitRoleService") RoleService roleService,
                          @Qualifier("fitSubscriptionTypeService") SubscriptionTypeService subscriptionTypeService,
                          @Qualifier("accessTokenService") TokenService accessTokenService,
                          @Qualifier("refreshTokenService") TokenService refreshTokenService,
                          @Qualifier("refreshTokenCookieService") CookieService refreshTokenCookieService,
                          @Qualifier("fitAuthService") AuthService authService,
                          @Qualifier("imageStorageService") StorageService imageStorageService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
        this.subscriptionTypeService = subscriptionTypeService;
        this.encoder = encoder;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenCookieService = refreshTokenCookieService;
        this.authService = authService;
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        FitUserDetails userDetails = (FitUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        String accessToken = accessTokenService
                .generateTokenFromUsername(userDetails.getUsername());
        String refreshToken = refreshTokenService
                .generateTokenFromUsername(userDetails.getUsername());

        return ResponseEntity.ok().headers(createRefreshTokenCookie(refreshToken)).body(
                new LoginResponse(userDetails.getId(),
                        userDetails.getUsername(), accessToken, roles));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<SuccessMessage> registerUser(@Valid @RequestPart(value = "request") SignupRequest request,
                                                       @RequestPart(value = "image", required = false) MultipartFile image) {
        userService.validateEmail(request.getEmail());

        FitUser fitUser = createFitUser(request);

        if(image != null) {
            imageStorageService.store(image);
            fitUser.setImgURL(image.getOriginalFilename());
        }

        userService.saveFitUser(fitUser, roleService.createFitRoles(request.getRoles()),
                subscriptionTypeService.createFitUserSubscription(request.getSubscriptionTypeInfo().getSubscriptionTypeId(),
                        request.getSubscriptionTypeInfo().getContractNumber()));

        return ResponseEntity.ok(
                new SuccessMessage("User registered successfully!"));
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@CookieValue(value="refreshToken", required = false)
                                                      String requestRefreshToken) {
        RefreshToken rToken = (RefreshToken) refreshTokenService.validate(requestRefreshToken);
        FitUser user = rToken.getUser();
        String jwtToken = accessTokenService
                .generateTokenFromUser(user);
        String refreshToken = refreshTokenService
                .generateTokenFromUser(user);

        return ResponseEntity.ok()
                .headers(createRefreshTokenCookie(refreshToken))
                .body(new RefreshTokenResponse(jwtToken));
    }

    @GetMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SuccessMessage> logoutUser() {
        userService.logoutFitUserByEmail(authService.getEmail());
        return ResponseEntity.ok(
                new SuccessMessage("Log out successful!"));
    }


    private HttpHeaders createRefreshTokenCookie(String refreshToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, refreshTokenCookieService.createCookie(refreshToken,
                refreshTokenService.getExpirationMs()).toString());
        return httpHeaders;
    }

    private FitUser createFitUser(SignupRequest request) {
        return new FitUser(request.getName(), request.getSurname(),
                request.getPatronymic(), request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getAge(), request.getGender(),
                request.getBirthdate(), request.getTelephone(),
                request.getPassport(), request.getAddress());
    }
}
