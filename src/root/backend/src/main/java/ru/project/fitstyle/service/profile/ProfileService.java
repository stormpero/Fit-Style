package ru.project.fitstyle.service.profile;

import ru.project.fitstyle.model.user.FitUser;

public interface ProfileService {
    FitUser getUserByEmail(String email);
    FitUser getUserById(Long id);
}
