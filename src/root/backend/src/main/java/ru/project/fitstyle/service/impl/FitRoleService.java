package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.dao.RoleRepository;
import ru.project.fitstyle.model.dao.UserRepository;
import ru.project.fitstyle.model.dto.user.ERole;
import ru.project.fitstyle.model.dto.user.FitUser;
import ru.project.fitstyle.model.dto.user.Role;
import ru.project.fitstyle.service.RoleService;
import ru.project.fitstyle.service.exception.role.RoleNotFoundException;
import ru.project.fitstyle.service.exception.role.UsersWithRoleNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FitRoleService implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public FitRoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<Role> createFitRoles(Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles != null) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "coach":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_COACH)
                                .orElseThrow(() -> new RoleNotFoundException("Role cannot be found!"));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RoleNotFoundException("Role cannot be found!"));
                        roles.add(modRole);

                        break;
                }
            });
        }
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException("Role cannot be found!"));
        roles.add(userRole);

        return roles;
    }

    @Override
    public List<FitUser> getUserByRole(ERole role) {
        return userRepository.findByRoles(roleRepository.findByName(role)
                        .orElseThrow(() -> new RoleNotFoundException("Role with that name cannot be found!")))
                .orElseThrow(() -> new UsersWithRoleNotFoundException("There are no users with that role: " + role));
    }
}
