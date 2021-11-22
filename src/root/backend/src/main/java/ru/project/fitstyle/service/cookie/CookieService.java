package ru.project.fitstyle.service.cookie;

import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Service;

public interface CookieService {

    HttpCookie getCookie (String data, Long duration);
}
