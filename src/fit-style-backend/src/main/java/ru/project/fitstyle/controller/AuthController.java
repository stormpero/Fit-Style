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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.*;
import java.util.stream.Collectors;

import ru.project.fitstyle.model.entity.user.RefreshToken;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.controller.request.auth.LoginRequest;
import ru.project.fitstyle.controller.response.auth.LoginResponse;
import ru.project.fitstyle.controller.response.auth.RefreshTokenResponse;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.service.*;
import ru.project.fitstyle.service.impl.details.FitUserDetails;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final TokenService accessTokenService;

    private final TokenService refreshTokenService;

    private final CookieService refreshTokenCookieService;

    private final AuthService authService;

    @Autowired
    public AuthController(final AuthenticationManager authenticationManager,
                          final UserService userService,
                          @Qualifier("accessTokenService") final TokenService accessTokenService,
                          @Qualifier("refreshTokenService") final TokenService refreshTokenService,
                          final CookieService refreshTokenCookieService,
                          final AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenCookieService = refreshTokenCookieService;
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody final LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final FitUserDetails userDetails = ((FitUserDetails) authentication.getPrincipal());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final String accessToken = accessTokenService
                .generateTokenFromUsername(userDetails.getUsername());
        final String refreshToken = refreshTokenService
                .generateTokenFromUsername(userDetails.getUsername());

        return ResponseEntity.ok().headers(createRefreshTokenCookie(refreshToken)).body(
                new LoginResponse(userDetails.getId(),
                        userDetails.getUsername(), accessToken, roles));
    }

    @GetMapping("/refresh-token")
    public synchronized ResponseEntity<RefreshTokenResponse> refreshToken(@CookieValue(value="refreshToken", required = false)
                                                      final String requestRefreshToken) {
        final FitUser fitUser = ((RefreshToken) (refreshTokenService.validate(requestRefreshToken))).getUser();

        return ResponseEntity.ok()
                .headers(createRefreshTokenCookie(refreshTokenService
                        .generateTokenFromUser(fitUser)))
                .body(new RefreshTokenResponse(accessTokenService
                        .generateTokenFromUsername(fitUser.getEmail())));
    }

    @GetMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SuccessMessage> logoutUser() {
        userService.logoutUserByEmail(authService.getEmail());
        return ResponseEntity.ok(
                new SuccessMessage("Log out successful!"));
    }


    private HttpHeaders createRefreshTokenCookie(final String refreshToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, refreshTokenCookieService.createCookie(refreshToken,
                refreshTokenService.getExpirationMs()).toString());
        return httpHeaders;
    }
}
