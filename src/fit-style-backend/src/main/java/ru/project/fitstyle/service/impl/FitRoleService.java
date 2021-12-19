package ru.project.fitstyle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.project.fitstyle.model.dto.user.RoleDto;
import ru.project.fitstyle.model.repository.RoleRepository;
import ru.project.fitstyle.model.entity.user.Role;
import ru.project.fitstyle.service.RoleService;
import ru.project.fitstyle.service.exception.role.RoleAlreadyExistsException;
import ru.project.fitstyle.service.exception.role.RoleNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class FitRoleService implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public FitRoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> createRoles(final List<String> strRoles) {
        List<Role> roles = new ArrayList<>();

        if (strRoles != null) {
            strRoles.forEach(role -> {
                switch (role) {
                    case "coach":
                        final Role adminRole = roleRepository.findByName("ROLE_COACH")
                                .orElseThrow(() -> new RoleNotFoundException("Role cannot be found!"));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        final Role modRole = roleRepository.findByName("ROLE_MODERATOR")
                                .orElseThrow(() -> new RoleNotFoundException("Role cannot be found!"));
                        roles.add(modRole);

                        break;
                }
            });
        }
        final Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RoleNotFoundException("Role cannot be found!"));
        roles.add(userRole);

        return roles;
    }

    @Override
    public void addRole(String name) {
        if(!roleRepository.existsByName(name)) {
            Role role = new Role(name);
            roleRepository.save(role);
        } else {
            throw new RoleAlreadyExistsException("Given already in database!");
        }
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAllRoles();
    }
}
