package ru.project.fitstyle.service;

import org.springframework.http.HttpCookie;

public interface CookieService {

    HttpCookie createCookie(final String data, final Long duration);
}
