package ru.project.fitstyle.service.impl.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.model.repository.FitUserRepository;


@Service
public class FitUserDetailsService implements UserDetailsService {

    private final FitUserRepository fitUserRepository;

    @Autowired
    public FitUserDetailsService(final FitUserRepository fitUserRepository) {
        this.fitUserRepository = fitUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final FitUser fitUser = fitUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return FitUserDetails.build(fitUser);
    }
}
