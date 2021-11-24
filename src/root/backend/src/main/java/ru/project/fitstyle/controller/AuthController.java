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
import ru.project.fitstyle.exception.email.EEmailError;
import ru.project.fitstyle.exception.email.EmailException;
import ru.project.fitstyle.exception.refresh.ERefreshTokenError;
import ru.project.fitstyle.exception.refresh.RefreshTokenException;
import ru.project.fitstyle.model.user.RefreshToken;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.payload.request.auth.LoginRequest;
import ru.project.fitstyle.payload.request.auth.SignupRequest;
import ru.project.fitstyle.payload.response.auth.LoginResponse;
import ru.project.fitstyle.payload.response.auth.RefreshTokenResponse;
import ru.project.fitstyle.payload.responsemessage.SuccessMessage;
import ru.project.fitstyle.repository.RefreshTokenRepository;
import ru.project.fitstyle.service.auth.AuthService;
import ru.project.fitstyle.service.cookie.CookieService;
import ru.project.fitstyle.service.storage.StorageService;
import ru.project.fitstyle.service.token.TokenService;
import ru.project.fitstyle.service.user.FitUserService;
import ru.project.fitstyle.service.user.UserDetailsImpl;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final FitUserService fitUserService;

    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder encoder;

    private final TokenService accessTokenService;

    private final TokenService refreshTokenService;

    private final CookieService refreshTokenCookieService;

    private final AuthService authServiceImpl;

    private final StorageService fileSystemStorageService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          RefreshTokenRepository refreshTokenRepository, PasswordEncoder encoder,
                          @Qualifier("fitUserServiceImpl") FitUserService fitUserService,
                          @Qualifier("accessTokenService") TokenService accessTokenService,
                          @Qualifier("refreshTokenService") TokenService refreshTokenService,
                          @Qualifier("refreshTokenCookieService") CookieService refreshTokenCookieService,
                          @Qualifier("authServiceImpl") AuthService authServiceImpl,
                          @Qualifier("fileSystemStorageService") StorageService fileSystemStorageService) {
        this.authenticationManager = authenticationManager;
        this.fitUserService = fitUserService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.encoder = encoder;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenCookieService = refreshTokenCookieService;
        this.authServiceImpl = authServiceImpl;
        this.fileSystemStorageService = fileSystemStorageService;
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                                          @RequestParam(value = "image", required = false) MultipartFile image) {
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
    public ResponseEntity<SuccessMessage> registerUser(@Valid @RequestBody SignupRequest signUpRequest,
                                                       @RequestParam(value = "image", required = false) MultipartFile image) {
        if (!fitUserService.existsByEmail(signUpRequest.getEmail())) {
            FitUser fitUser = new FitUser(signUpRequest.getName(), signUpRequest.getSurname(),
                    signUpRequest.getPatronymic(), signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getAge(), signUpRequest.getGender(),
                    signUpRequest.getBirthdate(), signUpRequest.getTelephone(),
                    signUpRequest.getPassport(), signUpRequest.getAddress());

            fitUserService.saveFitUser(fitUser, signUpRequest.getRoles(),
                    signUpRequest.getSubscriptionTypeId(), signUpRequest.getContractNumber());

            return ResponseEntity.ok(
                    new SuccessMessage("User registered successfully!"));
        }
        else {
            throw new EmailException(EEmailError.OCCUPIED);
        }
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
        fitUserService.logoutFitUserByEmail(authServiceImpl.getEmail());
        return ResponseEntity.ok(
                new SuccessMessage("Log out successful!"));
    }


    private HttpHeaders createRefreshTokenCookie(String refreshToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, refreshTokenCookieService.getCookie(refreshToken,
                refreshTokenService.getExpirationMs()).toString());
        return httpHeaders;
    }
}
