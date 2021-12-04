package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.repository.RoleRepository;
import ru.project.fitstyle.model.repository.FitUserRepository;
import ru.project.fitstyle.model.entity.user.ERole;
import ru.project.fitstyle.model.entity.user.Role;
import ru.project.fitstyle.service.RoleService;
import ru.project.fitstyle.service.exception.role.RoleNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FitRoleService implements RoleService {
    private final RoleRepository roleRepository;
    private final FitUserRepository fitUserRepository;

    @Autowired
    public FitRoleService(RoleRepository roleRepository, FitUserRepository fitUserRepository) {
        this.roleRepository = roleRepository;
        this.fitUserRepository = fitUserRepository;
    }

    @Override
    public List<Role> createRoles(List<String> strRoles) {
        List<Role> roles = new ArrayList<>();

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
}
