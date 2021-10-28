package ru.project.fitstyle.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.models.User;
import ru.project.fitstyle.payload.response.MessageResponse;
import ru.project.fitstyle.payload.response.UserProfileResponse;
import ru.project.fitstyle.repository.UserRepository;

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

    @GetMapping()
    public ResponseEntity<?> getUserProfileInfo(@RequestParam("id") Long id)
    {
        Optional<User> user = userRepository.findById(id);
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

    @GetMapping("/roles")
    public ResponseEntity<?> getUserRoles(@RequestParam("id") Long id)
    {
        Optional<User> user = userRepository.findById(id);
        User returnUser = user.orElse(null);
        if(returnUser==null)
        {
            return ResponseEntity.badRequest().
                    body(new MessageResponse("Error: user with that id doesn't exist!"));
        }
        return ResponseEntity.ok(returnUser.getRoles());
    }
}
