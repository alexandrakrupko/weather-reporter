package weather.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import weather.security.SecurityProperties;

@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {

    private final SecurityProperties securityProperties;

    public String generate(@NonNull String username) {
        return Jwts.builder()
                .subject(username)
                .signWith(Keys.hmacShaKeyFor(
                        Decoders.BASE64.decode(
                                securityProperties.getJwt().getSecretKey())))
                .compact();
    }
}
