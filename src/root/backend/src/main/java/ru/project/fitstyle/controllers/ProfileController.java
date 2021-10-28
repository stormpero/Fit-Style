package ru.project.fitstyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.models.User;
import ru.project.fitstyle.payload.request.profile.UserRequest;
import ru.project.fitstyle.payload.response.MessageResponse;
import ru.project.fitstyle.payload.response.UserProfileResponse;
import ru.project.fitstyle.repository.UserRepository;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    UserRepository userRepository;

    @Autowired
    public ProfileController (UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> getUserProfileInfo(@Valid @RequestBody UserRequest userRequest,
                                                BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: invalid id!"));
        }
        Optional<User> user = userRepository.findById(userRequest.getId());
        User returnUser = user.orElse(
                null
        );
        if(returnUser==null)
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: user with that id doesn't exist!"));
        }
        return ResponseEntity.ok(
                new UserProfileResponse(returnUser.getUsername(),
                        returnUser.getSurname(),
                        returnUser.getPatronymic(),
                        returnUser.getEmail(),
                        returnUser.getAge(),
                        returnUser.getGender(),
                        returnUser.getBirthdate(),
                        returnUser.getTelephone(),
                        returnUser.getPassport(),
                        returnUser.getAddress())
        );
    }

    @PostMapping("/roles")
    public ResponseEntity<?> getUserRoles(@Valid @RequestBody UserRequest userRequest,
                                          BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: invalid id!"));
        }
        Optional<User> user = userRepository.findById(userRequest.getId());
        User returnUser = user.orElse(null);
        if(returnUser==null)
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: user with that id doesn't exist!"));
        }
        return ResponseEntity.ok(returnUser.getRoles());
    }
}
