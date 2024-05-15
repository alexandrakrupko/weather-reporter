package weather.security.jwt;

import io.jsonwebtoken.Jwts;
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
public class JwtTokenGenerator {

    private final SecurityProperties securityProperties;

    public String generate(@NonNull String username) {
        String token = Jwts.builder()
                .subject(username)
                .signWith(Keys.hmacShaKeyFor(
                        Decoders.BASE64.decode(
                                securityProperties.getJwt().getSecretKey())))
                .compact();
        log.info("JWT token generated with username '{}'", username);
        return token;
    }
}
