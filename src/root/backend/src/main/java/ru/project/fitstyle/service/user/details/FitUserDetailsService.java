package ru.project.fitstyle.service.user.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.fitstyle.model.dto.user.FitUser;
import ru.project.fitstyle.model.dao.UserRepository;


@Service
public class FitUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public FitUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FitUser fitUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return FitUserDetails.build(fitUser);
    }
}
