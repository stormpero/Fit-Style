package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ru.project.fitstyle.exception.auth.email.EEmailError;
import ru.project.fitstyle.exception.auth.email.EmailException;
import ru.project.fitstyle.exception.auth.role.ERoleError;
import ru.project.fitstyle.exception.auth.role.RoleException;
import ru.project.fitstyle.exception.auth.refresh.ERefreshTokenError;
import ru.project.fitstyle.exception.auth.refresh.RefreshTokenException;
import ru.project.fitstyle.model.user.ERole;
import ru.project.fitstyle.model.user.RefreshToken;
import ru.project.fitstyle.model.user.Role;
import ru.project.fitstyle.model.user.User;
import ru.project.fitstyle.payload.request.auth.LogOutRequest;
import ru.project.fitstyle.payload.request.auth.LoginRequest;
import ru.project.fitstyle.payload.request.auth.SignupRequest;
import ru.project.fitstyle.payload.response.auth.LoginResponse;
import ru.project.fitstyle.payload.response.auth.RefreshTokenResponse;
import ru.project.fitstyle.payload.message.SuccessMessage;
import ru.project.fitstyle.repository.RoleRepository;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.service.token.AccessTokenService;
import ru.project.fitstyle.service.token.RefreshTokenService;
import ru.project.fitstyle.service.user.UserDetailsImpl;
import ru.project.fitstyle.security.util.CookieUtil;


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final AccessTokenService accessTokenService;

    private final RefreshTokenService refreshTokenService;

    private final CookieUtil cookieUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder encoder, AccessTokenService accessTokenService,
                          RefreshTokenService refreshTokenService, CookieUtil cookieUtil) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
        this.cookieUtil = cookieUtil;
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
                .generateToken(authentication);
        String refreshToken = refreshTokenService
                .generateToken(userDetails.getId());

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
        User user = new User(
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

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(
                new SuccessMessage("User registered successfully!"));
    }

    @GetMapping("/refreshtoken")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@CookieValue(value=("${authentication.auth.authRefreshTokenCookieName}"), required = false)
                                                      String requestRefreshToken) {
        return refreshTokenService
                .findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String jwtToken = accessTokenService
                            .generateTokenFromUsername(user.getEmail());
                    String refreshToken = refreshTokenService
                            .generateTokenFromUser(user);

                    return ResponseEntity.ok()
                            .headers(createRefreshTokenCookie(refreshToken))
                            .body(new RefreshTokenResponse(jwtToken));
                })
                .orElseThrow(() ->
                        new RefreshTokenException(requestRefreshToken,
                                ERefreshTokenError.NOT_FOUND));
    }

    @PostMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SuccessMessage> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        refreshTokenService
                .deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok(
                new SuccessMessage("Log out successful!"));
    }


    HttpHeaders createRefreshTokenCookie(String refreshToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createRefreshTokenCookie(refreshToken,
                refreshTokenService.getRefreshTokenExpirationMs()).toString());
        return httpHeaders;
    }
}
