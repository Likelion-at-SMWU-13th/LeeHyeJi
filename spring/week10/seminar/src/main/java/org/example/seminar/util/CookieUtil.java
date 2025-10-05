package org.example.seminar.util;

import jakarta.servlet.http.Cookie;

public class CookieUtil {
    public static Cookie creatCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
