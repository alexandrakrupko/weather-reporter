package weather.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import weather.security.SecurityProperties;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenValidator {

    private final SecurityProperties securityProperties;

    public boolean validate(@NonNull String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(
                            Decoders.BASE64.decode(
                                    securityProperties.getJwt().getSecretKey())))
                    .build()
                    .parse(token);
        } catch (ExpiredJwtException | MalformedJwtException | SecurityException | IllegalArgumentException e) {
            log.debug("Token validation failed: {}", e.getMessage());
            return false;
        }
        log.debug("Token validation succeed");
        return true;
    }
}
