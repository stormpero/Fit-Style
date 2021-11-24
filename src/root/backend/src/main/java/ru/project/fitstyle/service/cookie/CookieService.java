package ru.project.fitstyle.service.cookie;

import org.springframework.http.HttpCookie;

public interface CookieService {

    HttpCookie createCookie(String data, Long duration);
}
