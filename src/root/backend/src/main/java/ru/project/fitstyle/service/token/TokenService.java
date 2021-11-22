package ru.project.fitstyle.service.token;

import ru.project.fitstyle.model.user.FitUser;

public interface TokenService {

    String generateTokenFromUser(FitUser user);

    String generateTokenFromUsername(String username);

    String getUsernameFromToken(String token);

    boolean validate(Object obj);

    String getSecret();

    Long getExpirationMs();
}
