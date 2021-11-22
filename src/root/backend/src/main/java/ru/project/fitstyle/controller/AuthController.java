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

import ru.project.fitstyle.exception.email.EEmailError;
import ru.project.fitstyle.exception.email.EmailException;
import ru.project.fitstyle.exception.role.ERoleError;
import ru.project.fitstyle.exception.role.RoleException;
import ru.project.fitstyle.exception.refresh.ERefreshTokenError;
import ru.project.fitstyle.exception.refresh.RefreshTokenException;
import ru.project.fitstyle.exception.subscriptionType.ESubscriptionTypeError;
import ru.project.fitstyle.exception.subscriptionType.SubscriptionTypeException;
import ru.project.fitstyle.model.subscription.Subscription;
import ru.project.fitstyle.model.subscription.SubscriptionType;
import ru.project.fitstyle.model.user.ERole;
import ru.project.fitstyle.model.user.RefreshToken;
import ru.project.fitstyle.model.user.Role;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.payload.request.auth.LoginRequest;
import ru.project.fitstyle.payload.request.auth.SignupRequest;
import ru.project.fitstyle.payload.response.auth.LoginResponse;
import ru.project.fitstyle.payload.response.auth.RefreshTokenResponse;
import ru.project.fitstyle.payload.responsemessage.SuccessMessage;
import ru.project.fitstyle.repository.RefreshTokenRepository;
import ru.project.fitstyle.repository.RoleRepository;
import ru.project.fitstyle.repository.SubscriptionTypeRepository;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.service.auth.AuthService;
import ru.project.fitstyle.service.cookie.CookieService;
import ru.project.fitstyle.service.token.TokenService;
import ru.project.fitstyle.service.user.UserDetailsImpl;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final RoleRepository roleRepository;

    private final SubscriptionTypeRepository subscriptionTypeRepository;

    private final PasswordEncoder encoder;

    private final TokenService accessTokenService;

    private final TokenService refreshTokenService;

    private final CookieService refreshTokenCookieService;

    private final AuthService authServiceImpl;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RefreshTokenRepository refreshTokenRepository, RoleRepository roleRepository,
                          SubscriptionTypeRepository subscriptionTypeRepository,
                          PasswordEncoder encoder,
                          @Qualifier("accessTokenService") TokenService accessTokenService,
                          @Qualifier("refreshTokenService") TokenService refreshTokenService,
                          @Qualifier("refreshTokenCookieService") CookieService refreshTokenCookieService,
                          @Qualifier("authServiceImpl") AuthService authServiceImpl) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.roleRepository = roleRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.encoder = encoder;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenCookieService = refreshTokenCookieService;
        this.authServiceImpl = authServiceImpl;
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
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
    public ResponseEntity<SuccessMessage> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository
                .existsByEmail(signUpRequest.getEmail())) {
            throw new EmailException(EEmailError.OCCUPIED);
        }
        // Create new user's account
        FitUser fitUser = new FitUser(
						signUpRequest.getName(),
						signUpRequest.getSurname(),
						signUpRequest.getPatronymic(),
						signUpRequest.getEmail(),
						encoder.encode(signUpRequest.getPassword()),
						signUpRequest.getAge(),
						signUpRequest.getGender(),
						signUpRequest.getBirthdate(),
						signUpRequest.getTelephone(),
						signUpRequest.getPassport(),
						signUpRequest.getAddress());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles != null) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "coach":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RoleException(ERoleError.NOT_FOUND));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RoleException(ERoleError.NOT_FOUND));
                        roles.add(modRole);

                        break;
                }
            });
        }
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RoleException(ERoleError.NOT_FOUND));
        roles.add(userRole);

        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(signUpRequest.getSubscriptionTypeId())
                .orElseThrow(() -> new SubscriptionTypeException(ESubscriptionTypeError.NOT_FOUND));

        Subscription subscription = new Subscription();
        Date beginDate = new Date(new Date().getTime());
        Date endDate = new Date(beginDate.getTime() + subscription.getSubscriptionType().getValidity().getTime());
        subscription.setBeginDate(beginDate);
        subscription.setEndDate(endDate);
        subscription.setSubscriptionType(subscriptionType);
        subscription.setContractNumber(signUpRequest.getContractNumber());

        fitUser.setRoles(roles);
        fitUser.setSubscription(subscription);

        userRepository.save(fitUser);

        return ResponseEntity.ok(
                new SuccessMessage("User registered successfully!"));
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@CookieValue(value="refreshToken", required = false)
                                                      String requestRefreshToken) {
        RefreshToken rt = refreshTokenRepository.findByToken(requestRefreshToken)
                .orElseThrow(() ->
                        new RefreshTokenException(requestRefreshToken,
                                ERefreshTokenError.NOT_FOUND));
        if(refreshTokenService.validate(rt)) {
            FitUser user = rt.getUser();
            String jwtToken = accessTokenService
                    .generateTokenFromUser(user);
            String refreshToken = refreshTokenService
                    .generateTokenFromUser(user);

            return ResponseEntity.ok()
                    .headers(createRefreshTokenCookie(refreshToken))
                    .body(new RefreshTokenResponse(jwtToken));
        }
        else {
            throw new RefreshTokenException(rt.getToken(),
                    ERefreshTokenError.EXPIRED);
        }
    }

    @GetMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SuccessMessage> logoutUser() {
        refreshTokenRepository
                .deleteByFitUser(userRepository
                        .findByEmail(authServiceImpl
                                .getAuthentication().getName()).get());
        return ResponseEntity.ok(
                new SuccessMessage("Log out successful!"));
    }


    HttpHeaders createRefreshTokenCookie(String refreshToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, refreshTokenCookieService.getCookie(refreshToken,
                refreshTokenService.getExpirationMs()).toString());
        return httpHeaders;
    }
}
