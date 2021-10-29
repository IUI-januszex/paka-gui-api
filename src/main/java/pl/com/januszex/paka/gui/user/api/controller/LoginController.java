package pl.com.januszex.paka.gui.user.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.januszex.paka.gui.configuration.security.CookieAuthFilter;
import pl.com.januszex.paka.gui.configuration.security.JwtService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpServletResponse response) {
        Cookie cookie = getCookie("", -1);
        response.addCookie(cookie);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletResponse response) {
        Cookie cookie = getCookie(null, 0);
        response.addCookie(cookie);
        return ResponseEntity.noContent().build();
    }

    private Cookie getCookie(String value, int age) {
        Cookie cookie = new Cookie(CookieAuthFilter.COOKIE_TOKEN, value);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/api");
        cookie.setMaxAge(age);
        return cookie;
    }
}
