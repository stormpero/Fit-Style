package ru.project.fitstyle.service;

import ru.project.fitstyle.model.entity.user.FitUser;

public interface TokenService {

    String generateTokenFromUser(final FitUser user);

    String generateTokenFromUsername(final String username);

    String getUsernameFromToken(final String token);

    Object validate(final String token);

    Long getExpirationMs();
}
