package pl.com.januszex.paka.gui.user.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.com.januszex.paka.gui.configuration.security.CookieAuthFilter;
import pl.com.januszex.paka.gui.configuration.security.JwtService;
import pl.com.januszex.paka.gui.user.api.dao.LoginDao;
import pl.com.januszex.paka.gui.user.api.dto.LoginRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;
    private final LoginDao loginDao;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(loginDao.login(request).getToken(), -1);
        response.addCookie(cookie);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletResponse response) {
        Cookie cookie = getCookie(null, 0);
        response.addCookie(cookie);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('Admin')")
    public String foo() {
        return "foo";
    }

    private Cookie getCookie(String value, int age) {
        Cookie cookie = new Cookie(CookieAuthFilter.COOKIE_TOKEN, value);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(age);
        return cookie;
    }
}
