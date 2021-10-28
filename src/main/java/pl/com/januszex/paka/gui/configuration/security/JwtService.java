package pl.com.januszex.paka.gui.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("services.security")
public class JwtService {
    private String jwtSecret;
    private String audience;
    private String issuer;

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
                .requireIssuer(issuer)
                .requireAudience(audience)
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return UserData
                .builder()
                .id(Long.parseLong(claims.get("id", String.class)))
                .username(claims.get("login", String.class))
                .role(claims.get("role", String.class))
                .build();
    }
}
