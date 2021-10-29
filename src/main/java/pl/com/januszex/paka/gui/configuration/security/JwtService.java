package pl.com.januszex.paka.gui.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final String jwtSecret = "TODO";

    public boolean isTokenValid(String authToken) {
        if (authToken == null || authToken.equals("")) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException ignored) {
            return false;
        }
    }

    @SneakyThrows
    public UserData getAuthFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return UserData
                .builder()
                .id(claims.get("id", String.class))
                .username(claims.get("login", String.class))
                .role(claims.get("role", String.class))
                .build();
    }
}
