package pl.com.januszex.paka.gui.configuration.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieAuthFilter extends AbstractPreAuthenticatedProcessingFilter {

    public static final String COOKIE_TOKEN = "jwt";


    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return "N/A";
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        return Arrays.stream(cookies).filter(c -> c.getName().equals(COOKIE_TOKEN))
                .findAny().map(Cookie::getValue).orElse(null);
    }

}
