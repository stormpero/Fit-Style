package ru.project.fitstyle.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.request.permission.AddEditRoleRequest;
import ru.project.fitstyle.controller.response.SuccessMessage;
import ru.project.fitstyle.controller.response.permission.PermissionResponse;
import ru.project.fitstyle.service.AuthService;
import ru.project.fitstyle.service.RoleService;
import ru.project.fitstyle.service.UserService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/permission")
@PreAuthorize("hasRole('USER')")
public class PermissionController {

    private final UserService userService;

    private final AuthService authServiceImpl;

    private final RoleService roleService;

    @Autowired
    public PermissionController(final UserService userService,
                                final AuthService authServiceImpl,
                                final RoleService roleService) {
        this.userService = userService;
        this.authServiceImpl = authServiceImpl;
        this.roleService = roleService;
    }

    /**
     * Get all user roles
     * */
    @GetMapping("/roles")
    public ResponseEntity<PermissionResponse> getUserRoles() {
        return ResponseEntity.ok(
                new PermissionResponse(userService.getUserRolesByEmail(authServiceImpl.getEmail())));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @GetMapping("/all-roles")
    public ResponseEntity<PermissionResponse> getAllRoles() {
        return ResponseEntity.ok(
                new PermissionResponse(roleService.getAllRoles()));
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @PostMapping("/role")
    public ResponseEntity<SuccessMessage> add(@RequestBody AddEditRoleRequest request) {
        roleService.addRole(request.getName());
        return ResponseEntity.ok(new SuccessMessage("Role successfully added!"));
    }
}
