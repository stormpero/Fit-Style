package ru.project.fitstyle.service;

import ru.project.fitstyle.model.dto.user.FitUser;

public interface TokenService {

    String generateTokenFromUser(FitUser user);

    String generateTokenFromUsername(String username);

    String getUsernameFromToken(String token);

    Object validate(String token);

    Long getExpirationMs();
}
