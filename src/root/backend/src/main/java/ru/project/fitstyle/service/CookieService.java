package ru.project.fitstyle.service;

import org.springframework.http.HttpCookie;

public interface CookieService {

    HttpCookie createCookie(String data, Long duration);
}
