package ru.project.fitstyle.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.exception.profile.EProfileError;
import ru.project.fitstyle.exception.profile.ProfileException;
import ru.project.fitstyle.model.user.FitUser;
import ru.project.fitstyle.repository.UserRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public FitUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileException(EProfileError.NOT_FOUND));
    }

    @Override
    public FitUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ProfileException(EProfileError.NOT_FOUND));
    }
}
