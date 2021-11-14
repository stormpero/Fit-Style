package ru.project.fitstyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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

import ru.project.fitstyle.exception.ERefreshTokenError;
import ru.project.fitstyle.exception.TokenRefreshException;
import ru.project.fitstyle.models.user.ERole;
import ru.project.fitstyle.models.user.RefreshToken;
import ru.project.fitstyle.models.user.Role;
import ru.project.fitstyle.models.user.User;
import ru.project.fitstyle.payload.request.auth.LogOutRequest;
import ru.project.fitstyle.payload.request.auth.LoginRequest;
import ru.project.fitstyle.payload.request.auth.SignupRequest;
import ru.project.fitstyle.payload.request.auth.TokenRefreshRequest;
import ru.project.fitstyle.payload.response.auth.JwtResponse;
import ru.project.fitstyle.payload.response.auth.TokenRefreshResponse;
import ru.project.fitstyle.payload.response.utils.MessageResponse;
import ru.project.fitstyle.repository.RoleRepository;
import ru.project.fitstyle.repository.UserRepository;
import ru.project.fitstyle.security.jwt.JwtUtils;
import ru.project.fitstyle.security.services.RefreshTokenService;
import ru.project.fitstyle.security.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    private final RefreshTokenService refreshTokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils,
                          RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,
                                              HttpServletResponse httpServletResponse) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils
                .generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        refreshTokenService.deleteByUserId(userDetails.getId());
        RefreshToken refreshToken = refreshTokenService
                .createRefreshToken(userDetails.getId());

//        Cookie jwtTokenCookie = new Cookie("JSON Web Token", jwt);
//        jwtTokenCookie.setSecure(true);
//        jwtTokenCookie.setHttpOnly(true);
//
//        Cookie refreshTokenCookie = new Cookie("Refresh Token", refreshToken.getToken());
//        refreshTokenCookie.setSecure(true);
//        refreshTokenCookie.setHttpOnly(true);
//
//        httpServletResponse.addCookie(jwtTokenCookie);
//        httpServletResponse.addCookie(refreshTokenCookie);
        return ResponseEntity.ok(
                new JwtResponse(
                        jwt, refreshToken.getToken(), userDetails.getId(),
                        userDetails.getUsername(), roles));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository
                .existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
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

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles != null) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        } else {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(
                new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService
                .findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils
                            .generateTokenFromUsername(user.getEmail());
                    refreshTokenService.deleteByUserId(user.getId());
                    return ResponseEntity.ok(
                            new TokenRefreshResponse(token,
                                    refreshTokenService.save(
                                            refreshTokenService.createRefreshToken(user.getId())
                                    ).getToken()));
                })
                .orElseThrow(() ->
                        new TokenRefreshException(requestRefreshToken,
                                ERefreshTokenError.MISSED));
    }

    //TODO Frontend: post logout request every time user logouts
    @PostMapping("/logout")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        refreshTokenService
                .deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok(
                new MessageResponse("Log out successful!"));
    }

}
