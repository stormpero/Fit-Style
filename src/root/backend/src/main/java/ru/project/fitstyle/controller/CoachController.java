package ru.project.fitstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.project.fitstyle.controller.response.coach.AllCoachesResponse;
import ru.project.fitstyle.model.dto.user.CoachInfo;
import ru.project.fitstyle.model.entity.user.ERole;
import ru.project.fitstyle.model.entity.user.FitUser;
import ru.project.fitstyle.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/coach")
@PreAuthorize("hasRole('USER')")
public class CoachController {
    private final RoleService roleService;

    @Autowired
    public CoachController(@Qualifier("fitRoleService") RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping()
    public ResponseEntity<AllCoachesResponse> getAllCoaches() {
        List<FitUser> fitUserList = roleService.getUserByRole(ERole.ROLE_COACH);
        List<CoachInfo> allCoachesInfo = new ArrayList<>();
        for(FitUser fitUser : fitUserList) {
            allCoachesInfo.add(new CoachInfo(fitUser.getId(), fitUser.getName(),
                    fitUser.getSurname(), fitUser.getPatronymic()));
        }
        return ResponseEntity.ok(
                new AllCoachesResponse(allCoachesInfo)
        );
    }
}
